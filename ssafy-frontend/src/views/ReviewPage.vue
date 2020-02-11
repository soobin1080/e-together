<template>
  <div>
    <!-- <input type="text" name="title" v-model="title" />
    <input type="text" name="body" v-model="body" />
    <v-btn @click="postMyBudgets(title, body)">add</v-btn>-->
    <ImgBanner>
      <div
        class="text-center text-white"
        style="line-height:1.2em;font-size:2.5em;"
        slot="text"
        v-resize-text
      >Budget Review</div>
    </ImgBanner>
    <div class="main">
      <v-card style="width:80%;" class="mx-auto my-5 flat">
        <ReviewList :allReviews="allReviews"></ReviewList>
      </v-card>

      <div class="text-center">
        {{pages}}
        <v-pagination v-model="pages" :length="pagingLength" total-visible="9"></v-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import FirebaseService from "@/services/FirebaseService";
import ImgBanner from "../components/ImgBanner";
import ReviewList from "../components/ReviewList";
import ResizeText from "vue-resize-text";
import http from "../http-common";
export default {
  name: "MyListPage",

  components: {
    ImgBanner,
    ReviewList
  },
  directives: {
    ResizeText
  },
  data: () => ({
    title: "",
    body: "",
    pages: 1,
    allReviews: [],
    reviewPerPage: 5,
    items: [
      {
        icon: "folder",
        iconClass: "grey lighten-1 white--text",
        title: "Photos",
        subtitle: "Jan 9, 2014"
      },
      {
        icon: "folder",
        iconClass: "grey lighten-1 white--text",
        title: "Recipes",
        subtitle: "Jan 17, 2014"
      },
      {
        icon: "folder",
        iconClass: "grey lighten-1 white--text",
        title: "Work",
        subtitle: "Jan 28, 2014"
      }
    ]
  }),
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    getAllReviews() {
      http
        .get('/review')
          .then(res => {
            console.log(res)

          })
          .catch(err => {
            console.log(err)
          })
    }
  },
  computed: {
    // computedPagingList : () => ({
    //   async getMyBudget() {
    //     console.log("active")
    //     this.pagingList= await FirebaseService.getMyBudgets();
    //     return this.paginList
    //   }
    // })
  },
  mounted() {
    this.getAllReviews()
  }
};
</script>

<style scoped>
.main {
  padding-top: 80px;
  padding-bottom: 80px;
}
</style>