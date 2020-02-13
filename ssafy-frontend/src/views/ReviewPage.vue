<template v-resize-text>
  <div>
    <ImgBanner>
      <div
        class="text-center text-white"
        style="line-height:1.2em;font-size:2.5em;"
        slot="text"
        v-resize-text
      >Budget Review</div>
    </ImgBanner>
    
    <ReviewList :allReviews="allReviews"></ReviewList>
    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import ReviewList from "../components/ReviewList";
import ResizeText from "vue-resize-text";
import http from "../http-common";
import InfiniteLoading from 'vue-infinite-loading';
export default {
  name: "ReviewPage",

  components: {
    ImgBanner,
    ReviewList,
    InfiniteLoading,
  },
  directives: {
    ResizeText
  },
  data: () => ({
    page: 1,
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
    infiniteHandler($state) {
      http
        .get('/review', {
          // params: {
          //   page: this.page,
          // },
        }) //api에 url 삽입
        .then(response => {
          console.log('response')
          console.log(response)

            setTimeout(() => { //스크롤 페이징을 띄우기 위한 시간 지연(1초)
              if (response.data.length) {
                // this.page += 1
                this.allReviews = this.allReviews.concat(response.data);
                $state.loaded(); //데이터 로딩
                // this.limit += 10
                // console.log('allreview length : '+this.allreviews.leng) 
                if (this.allReviews.length > response.data.length) {
                  $state.complete(); //데이터가 없으면 로딩 끝
                }
              } else {
                $state.complete();
              }
            }, 1000)
          }).catch(error => {
            console.error(error);
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
    this.infiniteHandler($state)
  }
};
</script>

<style scoped>

</style>