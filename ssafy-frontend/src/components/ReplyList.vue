<template>
<v-container>
  <ul class="">
    <li v-for="reply in allReplys" 
      style="list-style-type:none;width:100%;left:0"
      class="position-relative">
      <Reply 
        :reply="reply"
        :class="floatWhere"
        @renewReply="upRenewReply"
        >
      </Reply>
      <br/>
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
    floatWhere() {
      
    },

    upRenewReply(reviewNum) {
      this.$emit('renewReply', reviewNum)
    }

  },
  mounted(){

  }
}
</script>

<style scoped>
li {

}
</style>