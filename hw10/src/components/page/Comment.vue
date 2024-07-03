<template>
  <div class="comment">
    <div class="user_name">
      {{ user.name }}
    </div>
    <div class="text">
      <p v-for="part in comment.text.split('\n')" :key="part">
      {{ part }}
    </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Comment",
  props: ["user", "comment"]
}
</script>

<style scoped>

</style>