<template>
<v-flex  xs12 md10 lg10 class="main">
  <v-layout mt-5 wrap style="width: 80%; margin: 0 auto">
    <v-flex v-for="review in computedAllReview" xs12 sm6 lg4 :key="review">
      <Review 
        :review="review" 
        class="ma-2"
        ></Review>
      
    </v-flex>
  </v-layout>
</v-flex>
</template>


<script>
  import FirebaseService from "@/services/FirebaseService";
  import Review from './Review'
  import ReviewDetail from './ReviewDetail'
  export default {
    name: "ReviewList",

    components: {
      Review,
      ReviewDetail,
    },

    props: {
      allReviews: {
        type: Array,
        // required: true
      },
    //   allLength: {
    //   type: Number,
    //   required: true
    // },
    },
  


    data() {
      return {

      }
    },

    methods: {

      
    },
    computed: {
      computedAllReview: function () {
        console.log("computedAllReviews")
        console.log(this.allReviews)
        return this.allReviews
      }
    },
    mounted() {}
  };
</script>

<style scoped>
.main{
  padding-top: 80px;
  padding-bottom: 80px;
  margin: auto;
 
}
</style>