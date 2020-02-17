<template>
<v-card
    max-width="344"
    class="m-3"
  >
    <v-list-item>
      <v-list-item-content>
        <v-list-item-title class="headline">{{computedReview.budget_title}}</v-list-item-title>
        <v-list-item-subtitle>by {{computedReview.name}}</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

    <v-img
      src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"
      height="194"
    ></v-img>

    <v-card-text>
    </v-card-text>

    <v-card-actions>
      <v-btn
        text
        :to="{ path: `reviewdetail/${this.review.review_num}`, params: { reviewNum: this.review.review_num }}"
        style="text-decoration:none; color:black;">
        상세 보기
      </v-btn>
      <!-- <v-btn
        text
      >
        Bookmark
      </v-btn> -->
      <v-spacer></v-spacer>
      {{computedReview.like_user.length}}
      <i v-if="isLiked" class="fas fa-heart ml-1" style="color: red;font-size:20px;" @click="like(review)"></i>
      <i v-else class="far fa-heart ml-1" style="color:black;font-size:20px;" @click="like(review)"></i>
    </v-card-actions>
  </v-card>
  
  <!-- <v-card height="330px">
    <v-img src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg" height="194"></v-img>
    <div>
      <div>
        <router-link 
        :to="{ path: `reviewdetail/${this.review.review_num}`, params: { reviewNum: this.review.review_num }}"
        style="text-decoration:none;">
          <v-card-title>
            {{computedReview.budget_title}}
          </v-card-title>
        </router-link>
        
        <v-card-subtitle>
          {{computedReview.name}}
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
  </v-card> -->
</template>

<script>
  import ResizeText from "vue-resize-text";
  import http from "../http-common";
  import EventBus from '../event-bus'
  import ReviewDetail from './ReviewDetail'
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
              // this.$emit('returnReview')
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
i { 
  cursor: pointer;
}

v-btn {

}
</style>