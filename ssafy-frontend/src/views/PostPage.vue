<template>
  <div>
    <ImgBanner>
      <div class="text-center text-white" style="line-height:1.2em;font-size:1.2em;" slot="text" v-resize-text>Post</div>
    </ImgBanner>
    <v-container>

      <!-- Post -->
      <v-layout>
        <v-flex xs12>
          <PostList :limits="4" :load-more="true"></PostList>
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
	components: {
		ImgBanner,
		PostList,
  },
  directives: {
    ResizeText
  }
}
</script>
