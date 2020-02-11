<template>
  <v-layout mt-5 wrap style="width: 80%; margin: 0 auto">
    <v-flex v-for="review in computedAllReview" xs12 sm6 lg4 :key="review.budget_title">
      <Review :review="review" class="ma-2"></Review>
    </v-flex>
  </v-layout>
</template>


<script>
  import FirebaseService from "@/services/FirebaseService";
  import Review from './Review'
  export default {
    name: "ReviewList",

    components: {
      Review
    },

    props: {
      allReviews: {
        type: Array,
        // required: true
      },
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
</style>