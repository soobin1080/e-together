<template>
  
  <div class="list-group-item">
    <!-- <i class="fas fa-file-alt" stlye="size:10px;"></i> -->
    <div>
      <b class="mr-2" style="">{{reply.name}}</b>

      <p class="d-inline mr-2" style="font-size:0.8rem">{{dateParsing(reply.reply_date)}}</p>
      <div v-if="userCheck" class="d-inline">
        <div v-if="isModify" class="d-inline">
          <span style="cursor:pointer" @click="modifyBtn" class="badge badge-primary mr-2">수정 취소</span>
          <span style="cursor:pointer" @click="replyModify" class="badge badge-info mr-2">수정하기</span>
        </div>
        <div v-else class="d-inline" >
          <span style="cursor:pointer" class="badge badge-primary mr-2" @click="modifyBtn">수정</span>
          <span style="cursor:pointer" class="badge badge-danger mr-2" @click="replyDelete">삭제</span>
        </div>
      </div>
      <br/>
      <br/>
    </div>
    <div v-if="isModify">
      <v-textarea
          outlined
          name="input-7-4"
          label="Outlined textarea"
          :value="reply.reply_content"
          v-model="modifyContent"
      ></v-textarea>
    </div>
    <div v-else>
      <span style="font-size:1.1rem">{{reply.reply_content}}</span>
    </div>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  name: 'Reply',
  props: {
    reply: {
      type: Object
    }
  },
  data(){
    return {
      userCheck: false,
      isModify: false,
      modifyContent: this.reply.reply_content,
    }
  }, 
  methods: {
    dateParsing(beforeParsing) {      
      const t = beforeParsing.indexOf('T')
      const afterParsing = beforeParsing.substring(0, t)      
      // console.log(afterParsing)
      const realdate=afterParsing.substring(0,4)+"년 "+afterParsing.substring(5,7)+"월 "+(Number(afterParsing.substring(8,11))+1)+"일"
      console.log("realdate: "+realdate)
      return realdate 
    },

    modifyBtn() {
      console.log('modify')
      console.log(this.isModify)
      this.isModify = !this.isModify
    },

    replyDelete() {
      let conf = confirm('댓글을 정말 삭제하시겠습니까?')
      if (conf === true) {
        if (this.reply.writer_email !== sessionStorage.getItem('email')) {
          alert('작성자만 댓글을 삭제할 수 있습니다')
          return
        } else {
          http
            .delete(`/reply/${this.reply.reply_num}`)
              .then(res => {
                alert('삭제되었습니다')
                this.$emit('renewReply', this.reply.review_num)
              })
            .catch(err => {
                console.log(err)
              })
        }
      }
    },

    replyModify() {
      
      const userEmail = sessionStorage.getItem('email')
      if (this.modifyContent === this.reply.reply_content || this.modifyContent === "") {
        alert('같거나 빈 내용으로 수정할 수 없습니다')
        return
      } else if (this.reply.writer_email !== userEmail) {
        alert('작성자만 수정이 가능합니다.')
      } else {
        console.log(this.reply)
        http
          .post(`/reply/update`, {
            review_num: this.reply.review_num,
            reply_num: this.reply.reply_num,
            reply_content: this.modifyContent,
            writer_email: this.reply.writer_email
          }, this.$store.state.requestHeader)
            .then(res => {
              console.log(res)
              alert('댓글 수정 완료')
              this.$emit('renewReply', this.reply.review_num)
              this.isModify = false
            })
            .catch(err => {
              console.log(err)
            })
      }
    }
  },
  computed: {

  },

  mounted(){
    if (sessionStorage.getItem('email') === this.reply.writer_email) {
      this.userCheck = true;
    }

  }
}
</script>

<style scoped>

</style>