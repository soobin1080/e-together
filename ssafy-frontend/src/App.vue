<template>
  <v-app>
    <v-content>
      <Header></Header>
      <router-view />
    </v-content>
  </v-app>
</template>

<script>
import store from "./store";
import Header from "./views/Header";

export default {
  name: "App",
  store,
  data() {
    return {
      //
    };
  },
  components: {
    Header
  }
};
</script>
