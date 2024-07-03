<template>
  <div>
    <Post v-for="post in Object.values(posts).sort((a, b) => b.id - a.id)" :post="post" :users="users" :comments="comments" :key="post.id" />
  </div>
</template>

<script>
import Post from "@/components/page/Post.vue";

export default {
    name: "Index",
    components: {Post},
    props: ["posts", "users", "comments"]
}
</script>

<style scoped>

</style>
