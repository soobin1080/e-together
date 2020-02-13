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
    <transition name="fade">
      <div class="loading" v-show="loading">
        <span class="fa fa-spinner fa-spin"></span> Loading
      </div>
    </transition>
    <ReviewList id="infinite-list" :allReviews="allReviews"></ReviewList>
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
    pages: 1,
    allReviews: [],
    reviewPerPage: 5,
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

    loadMore () {
      this.loading = true;
      setTimeout(e => {
        http
          .get('review', this.$store.getters.requestHeader)
          .then(res => {
            console.log(res)
              if (res.data.length < this.allReviews.length) {
                this.loading = false;
                return;
              }
              else {
                for (var i = (this.pages-1) * 6 ; i < this.pages*6; i++) {
                  this.allReviews.push(res.data[i]);
                  if (i === res.data.length) {
                    break;
                  }
                }
                this.pages++;
              }
          })
        this.loading = false;
        
      }, 200);
      /**************************************/
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
    const listElm = document.querySelector('#infinite-list');
    listElm.addEventListener('scroll', e => {
      if(listElm.scrollTop + listElm.clientHeight >= listElm.scrollHeight) {
        this.loadMore();
      }
    });
    // Initially load some items.
    this.loadMore();
  }
};
</script>

<style scoped>

</style>