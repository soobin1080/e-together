<template>
 <div>
    <v-img :src="getImgUrl('shopping-879498_1920.jpg')" aspect-ratio="5.5">
      <v-layout align-center justify-center row fill-height>
        <v-flex text-xs-center>
          <span class="text-shadow display-2 font-weight-light">
            <slot name="text" />
            <div
              class="text-center text-white"
              style="line-height:1.2em; font-size:2.5em;"
              slot="text"
              v-resize-text
            >Budget Review</div>
          </span>
        </v-flex>
      </v-layout>
    </v-img>
    
    <ReviewDetail
      :review="review"
      :budgetInfo="budgetInfo"
      :budgetList="budgetList"
      :like_users="like_users"
      :review_img="review_img"
      @getReviewDetailbyArg="getReviewDetailByArg">
    </ReviewDetail>

    <ReplyList 
      :allReplys="allReplys">
    </ReplyList>
    
    <v-container>
    <h4>댓글 달기</h4>
      <v-form
        ref="form"
      >

        <v-textarea
          outlined
          name="input-7-4"
          label="댓글을 입력해주세요"
          :model="content"
        ></v-textarea>


        <v-btn
          color="success"
          class="mr-4"
          @click="validate"
        >
          작성
        </v-btn> 
      </v-form>
    </v-container>
 </div>
</template>

<script>
import ResizeText from "vue-resize-text";
import ReviewDetail from "../components/ReviewDetail";
import ReplyList from "../components/ReplyList"
import http from "../http-common";
export default {
  name: "ReviewDetailPage",

  components: {
    ReviewDetail,
    ReplyList
  },
  directives: {
    ResizeText
  },
  data() {
    return {
      ReviewNum: 0,
      review: Object,
      budgetInfo: Object,
      budgetList: Array,
      liek_users: Array,
      allReplys: Array,
      title: "",
      content : "",
      total: 0,
    };
  },

  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    getReview(reviewNum) {
      console.log("getReview");
      http
        .get(
          `/review/${reviewNum}`,
          {
            review_num: reviewNum
          },
          this.$store.getters.requestHeader
        )
        .then(res => {
          console.log(res);
        });
    },
     getAllReplys() {
      console.log('getAllReplys')
      console.log(this.Review)
      const reviewNum = this.Review.review_num
      http 
        .get(`/reply/${reviewNum}`)
          .then(res => {
            console.log(res)
          })
          .catch(err => {
            console.log(err)
      })
    },
    getReviewDetail() {
      this.reviewNum = this.$route.params.reviewNum;
      console.log("getReviewDetail");
      http
        .get(
          `/review/${this.reviewNum}`,
          // { review_num: this.reviewNum },
          this.$store.getters.requestHeader
        )
        .then(res => {
          console.log(res);
          this.review = res.data.review;
          this.budgetInfo = res.data.budgetinfo;
          this.budgetList = res.data.budgetlist;
          console.log(this.budgetList);
          this.total = this.budgetList.reduce(
            (total, budget) => (total += budget.price * budget.quantity),
            0
          );
          this.like_users = res.data.like_user;
          this.review_img=res.data.review_img;
          let loginUser = sessionStorage.getItem("email");

          // if (this.like_users.includes(loginUser)) {
          //   document.getElementById("likeBtn").innerHTML = "좋아요 취소";
          //   document.getElementById("likeBtn").className =
          //     "badge badge-pill badge-danger";
          // } else {
          //   document.getElementById("likeBtn").innerHTML = "좋아요";
          //   document.getElementById("likeBtn").className =
          //     "badge badge-pill badge-primary";
          // }
        })
        .catch(err => {
          console.log(err);
        });
    },

    getReviewDetailByArg(review_num) {
      let loginUser = sessionStorage.getItem("email");
      http
        .get(
          `/review/${this.reviewNum}`,
          // { review_num: this.reviewNum },
          this.$store.getters.requestHeader
        )
        .then(res => {
          console.log(res);
          this.review = res.data.review;
          this.budgetInfo = res.data.budgetinfo;
          this.budgetList = res.data.budgetlist;
          this.like_users = res.data.like_user;
          this.review_img=res.data.review_img;
          // let loginUser = sessionStorage.getItem("email");
          // var likeBtn = document.getElementById("likeBtn");
          // console.log(likeBtn);
          // if (this.like_users.includes(loginUser)) {
          //   likeBtn.innerHTML = "좋아요 취소";
          //   likeBtn.className = "badge badge-pill badge-danger";
          // } else {
          //   likeBtn.innerHTML = "좋아요";
          //   likeBtn.className = "badge badge-pill badge-primary";
          // }
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
  mounted() {
    this.getReviewDetail()
    this.getAllReplys()
  }
};
</script>

<style scoped>

</style>