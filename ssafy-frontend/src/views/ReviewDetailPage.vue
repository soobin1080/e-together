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
        color="gray"
          outlined
          name="input-7-4"
          label="댓글을 입력해주세요"
          v-model="content"
        ></v-textarea>


        <v-btn
          color="success"
          class="mr-4"
          @click="writeReply"
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
      console.log('getAllReplys')
      const reviewNum = this.$route.params.reviewNum;
      console.log(reviewNum)
      http 
        .get(`/reply/${reviewNum}`)
          .then(res => {
            this.allReplys = res.data
            console.log(res)
          })
          .catch(err => {
            console.log(err)
      })
    },
    getAllReplysByArg(reviewNum) {
      console.log(reviewNum)
      http 
        .get(`/reply/${reviewNum}`)
          .then(res => {
            this.allReplys = res.data
            console.log(res)
          })
          .catch(err => {
            console.log(err)
      })
    },
    getReviewDetail() {
      this.reviewNum = this.$route.params.reviewNum;
      const name = this.$route.params.userName;
      console.log("getReviewDetail");
      console.log(this.$route.params)
      console.log(this.reviewNum)
      console.log(name)
      http
        .get(
          `/review/${this.reviewNum}`,
          // { review_num: this.reviewNum },
          this.$store.getters.requestHeader
        )
        .then(res => {
          console.log(res);
          this.review = res.data.review;
          this.review['name'] = name
          this.budgetInfo = res.data.budgetinfo;
          this.budgetList = res.data.budgetlist;
          console.log(this.budgetList);
          this.total = this.budgetList.reduce(
            (total, budget) => (total += budget.price * budget.quantity),
            0
          );
          this.like_users = res.data.like_user;
          let loginUser = sessionStorage.getItem("email");

        })
        .catch(err => {
          console.log(err);
        });
    },

    writeReply(){
      if (this.content === "") {
        alert('내용을 작성해주세요')
        return
      } else {
        http
          .post(`/reply`, {
            review_num: this.review.review_num,
            reply_content: this.content,
            writer_email: sessionStorage.getItem('email')
          }, this.$store.getters.requestHeader)
          . then(res => {
            console.log(res)
            alert('댓글 작성 완료')
            this.title = ""
            this.getAllReplys()
            // this.getAllReplysByArg(this.review.review_num)
          })
          .catch(err => {
            console.log(err)
          })
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