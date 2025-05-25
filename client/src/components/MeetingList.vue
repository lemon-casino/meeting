<template>
  <div class="p-6 max-w-xl mx-auto">
    <h1 class="text-2xl font-bold mb-4">Meetings</h1>
    <form class="flex mb-4" @submit.prevent="createMeeting">
      <input v-model="title" class="border flex-1 mr-2 px-2" placeholder="Meeting title" />
      <button type="submit" class="px-3 py-1 bg-blue-600 text-white rounded">Create</button>
    </form>
    <ul class="space-y-2">
      <li v-for="m in meetings" :key="m.id" class="flex items-center justify-between bg-gray-100 px-3 py-2 rounded">
        <span>{{ m.title }} ({{ m.participants.length }} participants)</span>
        <button @click="join(m.id)" class="px-2 py-1 bg-green-500 text-white rounded">Join</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
const emit = defineEmits(['join'])

const meetings = ref([])
const title = ref('')

async function load() {
  const res = await fetch('/api/meetings')
  meetings.value = await res.json()
}

async function createMeeting() {
  await fetch('/api/meetings', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title: title.value })
  })
  title.value = ''
  await load()
}

async function join(id) {
  const name = prompt('Your name:')
  if (!name) return
  await fetch(`/api/meetings/${id}/participants?name=${encodeURIComponent(name)}`, {
    method: 'POST'
  })
  emit('join', id)
}

onMounted(load)
</script>
