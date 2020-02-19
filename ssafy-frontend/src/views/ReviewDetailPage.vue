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
      @getReviewDetailbyArg="getReviewDetailByArg"
    ></ReviewDetail>

    <!-- 댓글 달기 -->

    <v-flex xs12 md8 lg8 class="main">
      <ReplyList
        :allReplys="allReplys"
        :user_email="budgetInfo.user_email"
        @renewReply="getAllReplysByArg"
      ></ReplyList>

      <v-container @click="logincheck()" class="mb-12" style="padding-left:37px">
        <h5 style="color:dimgrey">댓글 달기</h5>
        <v-form ref="form">
          <v-textarea
            color="orange"
            outlined
            name="input-7-4"
            label="댓글을 입력해주세요."
            v-model="content"
            no-resize
            style="resize:none; height:170px"
          ></v-textarea>

          <div style="text-align:right">
            <v-btn outlined color="success" class="mr-4" @click="writeReply">작성</v-btn>
          </div>
        </v-form>
      </v-container>
    </v-flex>
  </div>
</template>

<script>
import ResizeText from "vue-resize-text";
import ReviewDetail from "../components/ReviewDetail";
import ReplyList from "../components/ReplyList";
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
      content: "",
      total: 0
    };
  },

  methods: {
    logincheck() {
      let user = sessionStorage.getItem("email");
      console.log(user);
      if (user === null || typeof user === undefined) {
        alert("로그인이 필요합니다.");
        return;
      }
    },
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    getReview(reviewNum) {
      console.log("getReview");
      http
        .get(
          `review/${reviewNum}`,
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
      console.log("getAllReplys");
      const reviewNum = this.$route.params.reviewNum;
      console.log(reviewNum);
      http
        .get(`/reply/${reviewNum}`)
        .then(res => {
          this.allReplys = res.data;
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    getAllReplysByArg(reviewNum) {
      console.log(reviewNum);
      http
        .get(`/reply/${reviewNum}`)
        .then(res => {
          this.allReplys = res.data;
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    getReviewDetail() {
      this.reviewNum = this.$route.params.reviewNum;
      const name = this.$route.params.userName;
      console.log("getReviewDetail");
      console.log(this.$route.params);
      console.log(this.reviewNum);
      console.log(name);
      http
        .get(
          `/review/${this.reviewNum}`,
          // { review_num: this.reviewNum },
          this.$store.getters.requestHeader
        )
        .then(res => {
          console.log(res);
          this.review = res.data.review;
          this.review["name"] = name;
          this.budgetInfo = res.data.budgetinfo;
          this.budgetList = res.data.budgetlist;
          console.log(this.budgetList);
          this.total = this.budgetList.reduce(
            (total, budget) => (total += budget.price * budget.quantity),
            0
          );
          this.like_users = res.data.like_user;
          this.review_img = res.data.review_img;
          let loginUser = sessionStorage.getItem("email");
        })
        .catch(err => {
          console.log(err);
        });
    },

    writeReply() {
      if (this.content === "") {
        alert("내용을 작성해주세요");
        return;
      } else {
        http
          .post(
            `/reply`,
            {
              review_num: this.review.review_num,
              reply_content: this.content,
              writer_email: sessionStorage.getItem("email")
            },
            this.$store.getters.requestHeader
          )
          .then(res => {
            console.log(res);
            alert("댓글 작성 완료");
            this.title = "";
            this.getAllReplys();
            // this.getAllReplysByArg(this.review.review_num)
          })
          .catch(err => {
            console.log(err);
          });
      }
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
          this.review_img = res.data.review_img;
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  mounted() {
    this.getReviewDetail();
    this.getAllReplys();
  }
};
</script>

<style scoped>
.main {  
  margin: auto;  
}
</style>