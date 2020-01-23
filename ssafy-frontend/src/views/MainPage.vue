<template>
  <v-container class="main">
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field type="number" v-model="people" :step="10" label="인원" required></v-text-field>
      <v-text-field type="number" v-model="money" :step="10000" label="예산" required></v-text-field>

      <v-select
        v-model="select"
        :items="items"
        :rules="[v => !!v || 'Item is required']"
        label="마트"
        required
      ></v-select>
      
      <div class="btn">       
        <v-btn color="error" class="mr-4" @click="reset">Reset</v-btn>
        <v-btn :disabled="!valid" color="#ffec82" class="mr-4" @click="validate">장보기</v-btn>
      </div>
    </v-form>
  </v-container>
</template>
<script>
// import Vuex from 'vuex';
// Vue.use(Vuex);

// export const store=new Vuex.store({
//     state:{
//         people: this.people
//     }
// });
export default {
  data: () => ({
    valid: true,
    people: "",
    money: "",
    select: null,
    items: ["Item 1", "Item 2", "Item 3", "Item 4"]    
  }),

  methods: {
    validate() {
      if (this.$refs.form.validate()) {         
        this.snackbar = true;
        this.$router.push("/mart"); 
        this.$store.state.people=this.people;
        this.$store.state.money=this.money;
      }
    },
    reset() {
      this.$refs.form.reset();
    }   
  }  
};

</script>
<style scoped>
.main {
  padding-top: 120px;
  padding-bottom: 120px;
  width: 600px;
}
.btn {
  width: 580px;
  margin: auto;
}
</style>
