<template>
  <div class="list-group-item d-flex justify-content-around align-items-center">
    <span>{{reply.writer_email}}</span>
    <span>{{reply.reply_content}}</span>
    <span>{{reply.reply_date}}</span>
  </div>
</template>

<script>

export default {
  name: 'Reply',
  props: {
    reply: {
      type: Object
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