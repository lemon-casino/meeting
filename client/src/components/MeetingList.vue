<template>
  <div>
    <h1>Meetings</h1>
    <form @submit.prevent="createMeeting">
      <input v-model="title" placeholder="Meeting title" />
      <button type="submit">Create</button>
    </form>
    <ul>
      <li v-for="m in meetings" :key="m.id">
        {{ m.title }} ({{ m.participants.length }} participants)
        <button @click="join(m.id)">Join</button>
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
