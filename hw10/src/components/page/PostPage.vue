<template>
<div>
  <Post :post="post" :users="users" :comments="comments"/>
  <div class="form" v-if="users[post.userId]" @submit.prevent="writeComment">
    <div class="header">Write comment</div>
    <div class="body">
      <form method="post" action="">
        <div class="field">
          <input type="hidden" name="action" value="writeComment">
          <label for="text">
            Your comment:
          </label>
          <div class="value">
            <div class="enter-comment">
              <textarea id = "text" name="text" v-model="text"></textarea>
            </div>
          </div>
        </div>
        <div class="field error">{{ error }}</div>
        <div class="button-field">
          <input type="submit" value="Write">
        </div>
      </form>
    </div>
<!--    <div class="message" v-if="!error">-->
<!--      {{ "You created a post!" }}-->
<!--    </div>-->
  </div>
  <Comment v-for="comment in Object.values(comments).filter(cmmnt => cmmnt.postId === post.id).reverse()" :comment="comment" :user="users[comment.userId]" :key="comment.id"/>
</div>
</template>

<script>
import Post from "@/components/page/Post.vue";
import Comment from "@/components/page/Comment.vue";

export default {
  name: "PostPage",
  components: {Comment, Post},
  props: ["post", "users", "comments"],
  methods: {
    writeComment: function () {
      this.$root.$emit("onWriteComment", this.text, this.post.id);
    }
  },
  beforeCreate() {
    this.$root.$on("onWriteCommentValidationError", (error) => this.error = error);
  },
  data: function () {
    return {
      text: "",
      error: "ihbhj"
    }
  }
}
</script>

<style scoped>

</style>