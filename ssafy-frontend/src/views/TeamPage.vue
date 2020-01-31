<template>
  <div>
    <ImgBanner></ImgBanner>
    <v-container>
      <v-layout my-5>
        <v-flex lg12>
          <h2 class="headline mb-3">
            About
            <strong>E-Together</strong>
          </h2>
          <p>e-투계더는 emart 상품을 기반으로 워크샵, MT, 여행 등 단체로 장을 봐야 하는 상황에 카테고리 별로 필요한 상품을 보여주고 인원 별, 예산 별 상품을 추천해주는 서비스입니다. 선택한 상품의 가격을 계산해주고, 장보기 리스트를 저장할 수 있습니다. e-투계더 서비스로 합리적인 장보기를 해보세요.</p>
        </v-flex>
      </v-layout>

      <!-- About Me -->
      <v-layout my-5>
        <v-flex lg12>
          <h2 class="headline mb-3">
            About
            <strong>Team</strong>
          </h2>
          <v-img :src="getImgUrl('팀소개.png')" aspect-ratio="1.915" />
          <v-img :src="getImgUrl('프로젝트 진행사항.png')" aspect-ratio="1.915" />
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

export default {
  name: "HomePage",
  components: {
    ImgBanner,
    PortfolioList,
    PostList,
    RepositoryList
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
