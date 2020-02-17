<template>
  <ReviewDetail
  :review="review">
  </ReviewDetail>
</template>

<script>
  import ReviewDetail from '/.component/ReviewDetail'
  import { EventBus } from './event-bus'
  import http from "../http-common";
  export default {
    name : "ReviewDetailPage",

    components: {
      ReviewDetail
    },

    data(){ 
      return {
        ReviewNum : 0,
        Review: Object
      }
    },

    getReview(reviewNum) {
      console.log('getReview')
      http
        .get(`review/${reviewNum}`, {
          review_num: reviewNum
        }, this.$store.getters.requestHeader)
        .then(res => {
          console.log(res)
        }) 
    }


  },

  created(){
    EventBus.$on('goToDetail', reviewNum => {
      console.log('이벤트 받음' + reviewNum)
      this.getReview(reviewNum)
    })
  }
</script>

<style scoped>
</style>