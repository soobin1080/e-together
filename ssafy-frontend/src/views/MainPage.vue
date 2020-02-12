<template>
  <div>
    <v-img
      :src="getImgUrl('grilling-2491123_1920.jpg')" style="position:fixed; width:100%; height:100%; min-width:100%; min-height:100%;"
    ></v-img>
     <div class="hidden-md-and-down" style="position:absolute;">
        <p class="white--text pt-0" style="padding-left:100px; padding-right:100px">
          e-투계더는 emart 상품을 기반으로 워크샵, MT, 여행 등 단체로 장을 봐야 하는 상황에 카테고리 별로 필요한 상품을 보여주고 인원 별, 예산 별 상품을 추천해주는 서비스입니다.
          <br />선택한 상품의 가격을 계산해주고, 장보기 리스트를 저장할 수 있습니다. e-투계더 서비스로 합리적인 장보기를 해보세요.
        </p>        
      </div>
    <v-container class="main" fluid style="position:absolute;">
      
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-text-field
          type="number"
          v-model="personnel"
          :step="10"
          min="10"
          label="인원"
          required
          :rules="[v => !!v || '인원을 입력해 주세요']"
        ></v-text-field>
        <v-text-field
          type="number"
          v-model="budget"
          :step="10000"
          min="10000"
          label="예산"
          required
          :rules="[v => !!v || '예산을 입력해 주세요']"
        ></v-text-field>

        <!-- <v-select
        v-model="select"
        :items="items"
        :rules="[v => !!v || 'Item is required']"
        label="마트"
        required
        ></v-select>-->

        <div class="text-xs-center" style="text-align:center; float:center">
          <v-btn color="error" class="mr-4" @click="reset">Reset</v-btn>
          <v-btn :disabled="!valid" color="#ffd900" @click="validate">장보기</v-btn>
        </div>
      </v-form>
    </v-container>
  </div>
</template>
<script>
import ImgBanner from "../components/ImgBanner";
export default {
  components: {
    ImgBanner
  },
  data: () => ({
    valid: true,
    personnel: "",
    budget: ""

    // items: ["Item 1", "Item 2", "Item 3", "Item 4"]
  }),

  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
        this.$router.push("/product");
        this.$store.state.personnel = this.personnel;
        this.$store.state.budget = this.budget;
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    getImgUrl(img) {
      return require("../assets/" + img);
    }
  }
};
</script>
<style scoped>
.main {
  padding-top: 80px;
  padding-bottom: 80px;
  padding-left: 60px;
  padding-right: 60px;
  width: 580px;
  background-color:  rgba(255, 255, 255, 0.8);
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
/* .btn {
  width: 260px;
  margin: auto;
} */
</style>
