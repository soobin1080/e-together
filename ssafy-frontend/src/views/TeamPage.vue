<template>
  <div>
    <ImgBanner></ImgBanner>
    <v-container>
      <v-layout my-5>
        
        <v-flex lg12>
          <h2 class="headline mb-3">  About
            <strong>E-Together</strong></h2>    
            <v-row>
              <v-col lg="4">        
            <v-flex d-sm-flex>
          <v-img :src="getImgUrl('etogether.png')" aspect-ratio="2.3"/>
        </v-flex>
              </v-col>
              <v-col lg="8">
        <v-flex d-sm-flex>
          <p class="mr-4 text-center text-sm-left" >
            e-투계더는 emart 상품을 기반으로 워크샵, MT, 여행 등 단체로 장을 봐야 하는 상황에 카테고리 별로 필요한 상품을 보여주고 예산 별 카테고리 비율을 추천해주는 서비스입니다. 선택한 상품의 합계를 계산해주고, 장보기 리스트를 저장하고 pdf로 다운로드할 수 있습니다. 서비스 이용 후기를 등록하여 다른 사용자들과 장보기 내역을 공유하고 인원과 예산이 비슷한 사용자의 리뷰를 검색해 볼 수 있습니다. e-투계더 서비스로 합리적인 장보기를 해보세요.
          </p>
        </v-flex>
              </v-col>
            </v-row>
          </v-flex>
        
        
      </v-layout>
     

      <!-- About Me -->
      <v-layout my-5>
        <v-flex lg12>
          <h2 class="headline mb-3">
            About
            <strong>Team</strong>
          </h2>
          <v-flex mx-8 my-8>
          <v-img :src="getImgUrl('팀소개.png')" aspect-ratio="1.97" />
          
          </v-flex>
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
