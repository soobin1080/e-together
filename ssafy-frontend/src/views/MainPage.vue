<template>
  <div>
    <v-img
      :src="getImgUrl('grilling-2491123_1920.jpg')"
      style="position:fixed; width:100%; height:100%; min-width:100%; min-height:100%;"
    ></v-img>

    <v-container class="main" fluid style="position:absolute;">
      <v-row>
        <v-col lg="5" class="hidden-md-and-down">
          <div fluid style="position:relative; float:center; margin:auto; ">
            <v-img :src="getImgUrl('intro.png')" style="width:100%; height:100%; "></v-img>
            <p class="black--text pt-1" style="text-align:center">
              <br />
              <strong>e-투계더 서비스로 <br/>합리적인 장보기를 해보세요.</strong>
            </p>
          </div>
        </v-col>
        <v-col style="padding-top:80px">
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
          <br>
           <small style="padding-top:40px; margin:auto; color:red">* 마트 상품은 e-mart몰 상품을 기반으로 합니다.</small>
          
        </v-col>
      </v-row>
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
  padding-left: 60px;
  padding-right: 60px;
  padding-bottom: 60px;
  width: 800px;
  height:500px;
  top: 50%;
  left: 50%;
  background-color: rgba(255, 255, 255, 0.8);
  transform: translate(-50%, -50%);
}
</style>
