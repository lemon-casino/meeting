<template>
  <div class="max-w-xl mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Meetings</h1>
    <form @submit.prevent="createMeeting" class="flex gap-2 mb-4">
      <input
        v-model="title"
        placeholder="Meeting title"
        class="flex-1 border rounded px-2 py-1"
      />
      <button type="submit" class="bg-green-500 text-white px-4 py-1 rounded">
        Create
      </button>
    </form>
    <ul class="space-y-2">
      <li
        v-for="m in meetings"
        :key="m.id"
        class="bg-white shadow p-4 rounded flex justify-between items-center"
      >
        <div>
          <p class="font-semibold">{{ m.title }}</p>
          <p class="text-sm text-gray-500">{{ m.participants.length }} participants</p>
        </div>
        <button
          @click="join(m.id)"
          class="bg-blue-500 text-white rounded px-3 py-1 hover:bg-blue-600"
        >
          Join
        </button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

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
  await load()
}

onMounted(load)
</script>
