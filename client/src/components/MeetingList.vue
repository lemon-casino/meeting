<template>
  <div class="max-w-xl mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Meetings</h1>
    <form class="flex mb-4" @submit.prevent="createMeeting">
      <input v-model="title" placeholder="Meeting title" class="flex-1 border rounded-l px-2 py-1" />
      <button type="submit" class="bg-blue-600 text-white px-4 rounded-r">Create</button>
    </form>
    <ul class="space-y-2">
      <li v-for="m in meetings" :key="m.id" class="flex items-center justify-between bg-gray-100 p-2 rounded">
        <span>{{ m.title }} ({{ m.participants.length }} participants)</span>
        <button @click="join(m.id)" class="text-sm text-blue-600 underline">Join</button>

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
