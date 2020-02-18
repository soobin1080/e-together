<template>
<v-container>
  <ul class="list-group list-group-flush">
    <li v-for="reply in allReplys" style="list-style-type:none;">
      <Reply :reply="reply">
      </Reply>
    </li>
  </ul>
</v-container>
</template>

<script>
import Reply from './Reply'
export default {
  name: 'ReplyList',
  components: {
    Reply
  },
  props: {
    allReplys: {
      type: Array
    }
  }, 
  methods: {

  },
  mounted(){

  }
}
</script>

<style scoped>

</style>