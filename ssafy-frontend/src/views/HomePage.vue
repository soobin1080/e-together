<template>
  <div>
    <ImgBanner>
      <div class="text-center text-white" style="line-height:1.2em;" slot="text" v-resize-text>ahnzzi94<br/><p style="font-size:0.2em;">집수니의 바깥 나드리</p></div>
    </ImgBanner>
    <v-container>
      <!-- About Me -->
      <v-layout my-5>
        <v-flex lg12>
          <h2 class="headline mb-3"><strong>About Me</strong></h2>
          <p class="mr-4 text-center text-sm-left" >
            안녕하세요, SSAFY 참가자 여러분!
            <br />함께 프로젝트를 진행하게 되어서 기쁩니다. Vue는 어렵지 않습니다. 차근차근 하나씩 따라하다보면 어느새 멋진 블로그가
            만들어져 있을겁니다! 모두 화이팅 하시고, 꼭 완성해서 좋은 평가 있길 바라겠습니다.
          </p>
        </v-flex>
        <v-flex d-none d-sm-flex>
          <v-img :src="getImgUrl('0701-안지연170.jpg')" aspect-ratio="1.5" style="height:240px; width:180px" />
        </v-flex>
      </v-layout>

      <!-- Portfolio -->
      <v-layout my-5>
        <v-flex xs12>
          <h2 class="headline my-5 text-xs-center"><strong>Portfolio</strong></h2>                  
          <PortfolioList></PortfolioList>
        </v-flex>
      </v-layout>

      <!-- Post -->
      <v-layout my-5>
        <v-flex xs12>
          <h2 class="headline my-5 text-xs-center"><strong>Post</strong></h2>
          <PostList :column="2"></PostList>
        </v-flex>
      </v-layout>

      <!-- Github -->
      <v-layout my-5>
        <v-flex xs12>
          <h2 class="headline my-5 text-xs-center"><strong>Project</strong></h2>
          <RepositoryList></RepositoryList>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import PortfolioList from "../components/PortfolioList";
import PostList from "../components/PostList";
import RepositoryList from "../components/RepositoryList";
import ResizeText from "vue-resize-text";
//import LoginModal from "../components/LoginModal"

export default {
  name: "HomePage",
  components: {
    ImgBanner,
    PortfolioList,
    PostList,
    RepositoryList,
   // LoginModal,
  },
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    }
  },
  directives: {
    ResizeText
  }
};
</script>
