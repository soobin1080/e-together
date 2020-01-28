<template>
  <v-footer dark padless> 
    <v-card flat tile class="lightgray lighten-1 white--text text-center" width="100%">
      <div class="hidden-md-and-down">
      <v-card-text></v-card-text>

      <v-card-text class="white--text pt-0" style="padding-left:100px; padding-right:100px">
          e-투계더는 emart 상품을 기반으로 워크샵, MT, 여행 등 단체로 장을 봐야 하는 상황에 카테고리 별로 필요한 상품을 보여주고 인원 별, 예산 별 상품을 추천해주는 서비스입니다. <br>선택한 상품의 가격을 계산해주고, 장보기 리스트를 저장할 수 있습니다. e-투계더 서비스로 합리적인 장보기를 해보세요.
      </v-card-text>

      <v-divider></v-divider>
      </div>

      <v-card-text class="white--text">
        &copy; {{ new Date().getFullYear() }} —
        <strong @click="team">e-together</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>
<script>
export default {
  name: "Footer",
  methods:{
    team(){
      this.$router.push("/team");
    }
  }
};

</script>


