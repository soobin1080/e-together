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
    
      <ReviewDetail :review="review"></ReviewDetail>
    
 </div>
</template>

<script>
import ResizeText from "vue-resize-text";
import ReviewDetail from "../components/ReviewDetail";
import http from "../http-common";
export default {
  name: "ReviewDetailPage",

  components: {
    ReviewDetail
  },
  directives: {
    ResizeText
  },
  data() {
    return {
      ReviewNum: 0,
      Review: Object
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
    }
  }
};
</script>

<style scoped>

</style>