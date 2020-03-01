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
    <v-container>
    <transition name="fade">
      <div class="loading" v-show="loading">
        <span class="fa fa-spinner fa-spin"></span> Loading
      </div>
    </transition>
    <!-- <div
      v-infinite-scroll="loadMore" 
      infinite-scroll-disabled="busy" 
      :infinite-scroll-distance="reviewPerPage" >
      <ReviewList  
        :allReviews="allReviews"
        >
    </ReviewList>
    </div> -->
    <div class="search text-center mt-8" width="100%">
      <p style="text-align:center; color:darkblue; font-weight:bold">인원과 예산으로 리뷰 검색이 가능합니다! :)</p>
      <v-col cols="12" sm="6" md="3" class="d-inline-block">
        <v-text-field
          width="30%"
          v-model="personnel"
          @change="search(personnel, budget)"
          label="인원"
        ></v-text-field>
      </v-col>
      <v-col cols="12" sm="6" md="3" class="d-inline-block">
        <v-text-field
          width="30%"
          v-model="budget"
          @change="search(personnel, budget)"
          label="예산"
        ></v-text-field>
      </v-col>
      <v-btn small color="primary" 
        class="d-inline-block"
        @click="search()"
        >검색</v-btn>
    </div>
    <ReviewList
      :allReviews="allReviews"      
    >
    </ReviewList>

    <infinite-loading @infinite="infiniteHandler" :identifier="infiniteId"></infinite-loading>
    </v-container>
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
    page: 1,
    allReviews: [],
    reviewPerPage: 6,
    loading: false,
    personnel: "",
    budget: "",
    infiniteId : 1,
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
    search() {
      this.page = 1
      this.allReviews = []
      this.infiniteId += 1
    },
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    infiniteHandler($state) {
      let per = this.personnel === "" ? 0 : Number(this.personnel)
      let bud = this.budget === "" ? 0 : Number(this.budget)
      let requestUrl = per === 0 && bud === 0 ? `/review` : `/review/${per}/${bud}`
      http
        .get(requestUrl, {
        params: {
          page: this.page,
        },
      }).then(({ data }) => {
        console.log('infiniteHandler')
        console.log(requestUrl)
        console.log(data)
        if (data.reviewlist.length) {
          console.log(data.reviewlist)
          this.page += 1;
          this.allReviews = this.allReviews.concat((data.reviewlist));
          $state.loaded();
        } else {
          $state.complete();
        }
      });
     }
    // loadMore() {
    //   this.busy = true
    //   console.log('loadmore')
    //   setTimeout(() => {
    //     http
    //       .get('/review', this.$store.getters.requestHeader)
    //         .then(res => {
    //           if (this.allReviews.length < res.data.length) {
    //             for (let i = (this.pages-1)*this.reviewPerPage; i < this.pages*this.reviewPerPage; i++ ) {
    //               if (this.allReviews.length === res.data.length) {
    //                 this.busy = true
    //                 break;
    //               } else {
    //                 this.allReviews.push(res.data[i])
    //               }
    //             } 
    //             this.pages++;
    //             this.busy = false
    //           }
    //         })
    //   }, 100);
    //   }
  },
  computed: {
  },  
  mounted() {
   this.infiniteHandler($state)
  }
};
</script>

<style scoped>

</style>