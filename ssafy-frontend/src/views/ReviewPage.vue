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
    <transition name="fade">
      <div class="loading" v-show="loading">
        <span class="fa fa-spinner fa-spin"></span> Loading
      </div>
    </transition>
    <div
      v-infinite-scroll="loadMore" 
      infinite-scroll-disabled="busy" 
      :infinite-scroll-distance="reviewPerPage" >
      <ReviewList  
        :allReviews="allReviews"
        >
    </ReviewList>
    </div>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import ReviewList from "../components/ReviewList";
import ResizeText from "vue-resize-text";
import http from "../http-common";

export default {
  name: "ReviewPage",

  components: {
    ImgBanner,
    ReviewList,
  },
  directives: {
    ResizeText
  },
  data: () => ({
    title: "",
    body: "",
    busy: false,
    pages: 1,
    allReviews: [],
    reviewPerPage: 6,
    loading: false,
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
    loadMore() {
      this.busy = true
      console.log('loadmore')
      setTimeout(() => {
        http
          .get('/review', this.$store.getters.requestHeader)
            .then(res => {
              if (this.allReviews.length < res.data.length) {
                for (let i = (this.pages-1)*this.reviewPerPage; i < this.pages*this.reviewPerPage; i++ ) {
                  if (this.allReviews.length === res.data.length) {
                    this.busy = true
                    break;
                  } else {
                    this.allReviews.push(res.data[i])
                  }
                } 
                this.pages++;
                this.busy = false
              }
            })
      }, 100);
      }
  },
  computed: {
  },  
  mounted() {
    this.loadMore()
  }
};
</script>

<style scoped>

</style>