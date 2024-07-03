<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2022 by Mike Mirzayanov |
        Count of users: {{countUsers}} | Count of posts: {{countPosts}}
    </footer>
</template>

<script>
export default {
    name: "Footer",
  props: ["countUsers", "countPosts"],
}
</script>

<style scoped>

</style>