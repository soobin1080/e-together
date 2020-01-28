<template>
  <b-row class="main" style="padding-top:120px">
    <b-col cols="8">
      <!-- Tabs with card integration -->
      <v-text-field       
        width="100px"
        flat
        hide-details
        label="Search"
        prepend-inner-icon="search"
        solo-inverted
      ></v-text-field>
      <b-card no-body style="height:700px;">
        <b-tabs v-model="tabIndex" small card>
          <b-tab title="고기">고기보여줘!</b-tab>
          <b-tab title="야채">
            I'm the second tab
            <b-card>I'm the card in tab</b-card>
          </b-tab>
          <b-tab title="밥/라면">Sibzamini!</b-tab>
          <b-tab title="음료">I'm the last tab</b-tab>  
              
             
        </b-tabs>
         
      </b-card>
    </b-col>
    <b-col cols="4">
      <BudgetList></BudgetList>
    </b-col>
  </b-row>
</template>
<script>
import BudgetList from "../components/BudgetList";

export default {
  components: {
    BudgetList
  }
};
</script>

<style scoped>
.main {
  padding-bottom: 120px;
  margin: auto;
  width: 80%;
}
</style>
