<template>
  <v-container mb-5>
    <div style="margin:auto; padding-top:50px; width:80%">
      <h2>
        <strong>About Site</strong>
      </h2>

      <p>e-투계더는 emart 상품을 기반으로 워크샵, MT, 여행 등 단체로 장을 봐야 하는 상황에 카테고리 별로 필요한 상품을 보여주고 예산 별 카테고리 비율을 추천해주는 서비스입니다. 선택한 상품의 합계를 계산해주고, 장보기 리스트를 저장하고 pdf로 다운로드할 수 있습니다. 서비스 이용 후기를 등록하여 다른 사용자들과 장보기 내역을 공유하고 인원과 예산이 비슷한 사용자의 리뷰를 검색해 볼 수 있습니다. e-투계더 서비스로 합리적인 장보기를 해보세요.</p>
    </div>
    <v-layout style="margin:auto; padding-top:80px; width:80%">
      <v-flex>
        <h2 class="headline mb-1">
          <strong>1. REVIEW 페이지</strong>
        </h2>
        <v-flex>
          <v-img :src="getImgUrl('1reviewpage.png')" aspect-ratio="1.97" />
        </v-flex>

        <h2 class="headline mb-1">
          <strong>2. MART 페이지</strong>
        </h2>
        <v-flex>
          <v-img :src="getImgUrl('2martpage.png')" aspect-ratio="1.97" />
        </v-flex>

        <h2 class="headline mb-1">
          <strong>3. MY BUDGET 페이지</strong>
        </h2>
        <v-flex>
          <v-img :src="getImgUrl('3mybudgetpage.png')" aspect-ratio="1.97" />
        </v-flex>

        <h2 class="headline mb-1">
          <strong>4. REVIEW 페이지</strong>
        </h2>
        <v-flex>
          <v-img :src="getImgUrl('4reviewdetail.png')" aspect-ratio="1.97" />
        </v-flex>

        <h2 class="headline mb-1">
          <strong>5. MAP 페이지</strong>
        </h2>
        <v-flex>
          <v-img :src="getImgUrl('5mappage.png')" aspect-ratio="1.97" />
        </v-flex>
      </v-flex>
    </v-layout>
  </v-container>
  
</template>
<script>
export default {
  name: "IntroPage",
  components: {},
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    }
  }
};
</script>
