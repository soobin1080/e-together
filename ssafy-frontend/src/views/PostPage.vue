<template>
  <div>
    <ImgBanner>
      <div class="text-center text-white" style="line-height:1.2em;font-size:1.2em;" slot="text" v-resize-text>Post</div>
    </ImgBanner>
    <v-container>

      <!-- Post -->
      <v-layout>
        <v-flex xs12>
          <PostList :limits="limits" :load-more="loadMore" @morePost="loadMorePost"></PostList>
        </v-flex>
      </v-layout>

    </v-container>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import PostList from '../components/PostList'
import ResizeText from "vue-resize-text";

export default {
  name: 'PostPage',
  data() {
    return {
      limits: 6,
      loadMore: true,
    }
  },
  methods: {
    loadMorePost(postsLength) { // 계산한 값은 다시 하위 컴포넌트로 전달
      //console.log("postsLength : "+postsLength)
      if (this.limits + 4 > postsLength) {
        this.loadMore = false
        this.limits = postsLength
            } else {
            this.limits += 6
          }
      },
  },
	components: {
		ImgBanner,
		PostList,
  },
  directives: {
    ResizeText
  }
}
</script>
