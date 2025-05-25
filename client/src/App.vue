<template>
  <component :is="currentComponent" :id="currentId" @join="onJoin" @leave="onLeave" />
</template>

<script setup>
import { ref, computed } from 'vue'
import MeetingList from './components/MeetingList.vue'
import MeetingRoom from './components/MeetingRoom.vue'

const currentId = ref(null)

const currentComponent = computed(() => currentId.value ? MeetingRoom : MeetingList)

function onJoin(id) {
  currentId.value = id
}

function onLeave() {
  currentId.value = null
}
</script>
