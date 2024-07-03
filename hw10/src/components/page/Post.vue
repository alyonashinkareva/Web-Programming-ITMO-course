<template>
    <article>
      <div class="title">
        <a href="#" @click.prevent="onPostPage(post)">
          <p v-for="part in post.title.split('os')" :key="part">
            {{ part }}
          </p>
        </a>
      </div>
      <div class="information">By {{ users[post.userId].name }}</div>
      <div class="body">
        <p v-for="part in post.text.split('\n')" :key="part">
          {{ part }}
        </p>
      </div>
      <ul class="attachment">
        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
      </ul>
      <div class="footer">
        <div class="left">
          <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
          <span class="positive-score">+173</span>
          <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
        </div>
        <div class="right">
          <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
          <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
          <a href="#" @click.prevent="onPostPage(post)">{{ countComments(post.id) }}</a>
        </div>
      </div>
    </article>
</template>

<script>
export default {
  name: "Post",
  props: ["post", "comments", "users"],
  methods: {
    countComments: function (postId) {
      return (Object.values(this.comments).filter(comment => comment.postId === postId)).length;
    },
    onPostPage: function (post) {
      this.$root.$emit("onPostPage", post);
    }
  }
}
</script>

<style scoped>

</style>