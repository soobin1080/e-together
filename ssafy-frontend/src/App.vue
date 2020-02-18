<template>
  <v-app>
    <Header data-html2canvas-ignore="true"></Header>
    <v-content>
      <router-view />
    </v-content>
    <Footer data-html2canvas-ignore="true"></Footer>
  </v-app>
</template>

<script>
import store from "./store";
import Header from "./views/Header";
import Footer from "./views/Footer";
import {mapState, mapActions, mapGetters} from 'vuex'
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

