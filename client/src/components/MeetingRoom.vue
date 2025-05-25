<template>
  <div class="flex h-full">
    <div class="flex-1 bg-gray-900 flex items-center justify-center">
      <video ref="video" autoplay playsinline class="max-h-full"></video>
    </div>
    <div class="w-60 bg-gray-100 p-4 overflow-y-auto">
      <h2 class="font-bold mb-2">Participants</h2>
      <ul>
        <li v-for="p in participants" :key="p" class="mb-1">{{ p }}</li>
      </ul>
      <button @click="leave" class="mt-4 px-3 py-1 bg-red-500 text-white rounded">Leave</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'

const props = defineProps({
  id: String
})
const emit = defineEmits(['leave'])

const participants = ref([])
const video = ref(null)
let interval
let stream

async function load() {
  const res = await fetch(`/api/meetings/${props.id}`)
  if (res.ok) {
    const data = await res.json()
    participants.value = data.participants || []
  }
}

async function startShare() {
  try {
    stream = await navigator.mediaDevices.getDisplayMedia({ video: true })
    if (video.value) {
      video.value.srcObject = stream
    }
  } catch (e) {
    console.error(e)
  }
}

function leave() {
  if (stream) {
    stream.getTracks().forEach(t => t.stop())
  }
  clearInterval(interval)
  emit('leave')
}

onMounted(async () => {
  await load()
  await startShare()
  interval = setInterval(load, 3000)
})

onBeforeUnmount(() => {
  clearInterval(interval)
  if (stream) {
    stream.getTracks().forEach(t => t.stop())
  }
})
</script>
