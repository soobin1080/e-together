<template>
  <v-app>
    <Header></Header>
    <v-content>
      <router-view />
    </v-content>
    <Footer></Footer>
  </v-app>
</template>

<script>
import store from "./store";
import Header from "./views/Header";
import Footer from "./views/Footer";

export default {
  name: "App",
  store,
  data() {
    return {
      //
    };
  },
  components: {
    Header,
    Footer
  }
};
</script>

