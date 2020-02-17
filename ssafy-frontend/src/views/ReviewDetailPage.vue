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

    methods: {
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
  }
</script>

<style scoped>
</style>