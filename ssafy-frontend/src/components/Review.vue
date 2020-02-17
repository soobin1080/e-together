<template>
  <v-card height="330px">
    <v-img src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg" height="194"></v-img>
    <div>
      <div>
        <v-card-title @click="goToDetail(review.review_num)">
          {{computedReview.budget_title}}
        </v-card-title>
        <v-card-subtitle>
          {{computedReview.user_name}}
        </v-card-subtitle>
      </div>
      <div>
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn icon>
            {{computedReview.like_user.length}}
            <i v-if="isLiked" class="fas fa-heart" style="color: red;font-size:30px;" @click="like(review)"></i>
            <i v-else class="far fa-heart" style="color:black;font-size:30px;" @click="like(review)"></i>
          </v-btn>
          <v-btn icon>
            <v-icon>mdi-share-variant</v-icon>
          </v-btn>
        </v-card-actions>
      </div>
    </div>
  </v-card>
</template>

<script>
  import ResizeText from "vue-resize-text";
  import http from "../http-common";
  import EventBus from '../event-bus.js'
  export default {
    name: "Review",


    data() {
      return {
        isLiked: false,
      }
    },

    props: {
      review: {
        type: Object,
      }
    },

    methods: {
      likeCheck(like_users) {
        console.log('isLiked')
        const b = like_users.includes(sessionStorage.getItem('email'))
        this.isLiked = b ? true : false
      },
      like(review) {
        // console.log('like')
        let user = sessionStorage.getItem('email')
        console.log(user)
        if (user === null || typeof user === undefined) {
           alert('로그인이 필요합니다.')
           return
        }
        // console.log(review)
        if (this.review.like_user.includes(user)) {
          console.log('delete')
          console.log(review.review_num)
          console.log(user)
          http
            .delete(`/review/like_count/${review.review_num}/${user}`
            , this.$store.getters.requestHeader)
            .then(res => {
              // import('./ReviewList.vue')
              this.review.like_user = this.review.like_user.filter(u => {
               return u !== user
              })
              console.log(res)
            })
            .catch( err => {
              console.log(err)
            })
        } else {
          console.log('like')
          http
            .post(`/review/like_count`,{
              review_num: review.review_num,
              user_email: user
            }, this.$store.getters.requestHeader)
              .then(res => {
                console.log(res)
                this.review.like_user.push(user)
                
                this.isliked = true
              })
              .catch(err => {
                console.log(err)
                this.isliked = false
              })
        }
        this.isLiked = !this.isLiked
      },
      getUserEmail() {
        return sessionStorage.getItem('email')
      },
      goToDetail(review) {
        console.log('이벤트 보냄,' + this.review.review_num)
        EventBus.$emit('goToDetail', this.review.review_num)
      }
   },

    computed: {
      computedReview: function () {
        console.log('computedReview')
        this.likeCheck(this.review.like_user)
        const loginUser = sessionStorage.getItem('email')
        // this.isliked = this.review.like_user.includes(loginUser) ? true : false
        return this.review
      }
    }
  }
</script>

<style scoped>

</style>