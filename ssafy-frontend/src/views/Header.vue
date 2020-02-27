<template>
  <div>
    <LoginModal @checkLogIn="getUserName" @checkIsAdmin="getIsAdmin"></LoginModal>
    <v-navigation-drawer v-model="sidebar" fixed temporary>
      <v-list style="padding-top:10px;">
        <v-list-item style="padding:0px">
          <v-list-item-action></v-list-item-action>
          <router-link to="/" tag="span" style="cursor: pointer">
            <v-img :src="getImgUrl('etogether.png')" width="80px" />
          </router-link>
        </v-list-item>
        <v-list-item style="padding:0px" v-if="setUserName" @click="pwdCheck">
          <v-list-item-action></v-list-item-action>
          <i class="material-icons">account_circle</i>
          {{setUserName}}
        </v-list-item>
        <v-list-item @click="clickproduct">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content>Mart</v-list-item-content>
        </v-list-item>
        <v-list-item @click="clickmap">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content>Map</v-list-item-content>
          <!-- <v-list-item-content>{{ item.title }}</v-list-item-content> -->
        </v-list-item>
        <v-list-item v-if="username" @click="clickmybudget">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content>My Budget</v-list-item-content>
        </v-list-item>
        <v-list-item @click="clickreview">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content>Review</v-list-item-content>
          <!-- <v-list-item-content>{{ item.title }}</v-list-item-content> -->
        </v-list-item>
        <v-list-item v-if="checkAdmin" @click="goToAdminPage">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content>Admin</v-list-item-content>
          <!-- <v-list-item-content>{{ item.title }}</v-list-item-content> -->
        </v-list-item>
        <v-list-item v-if="setUserName" @click="logout">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content class="text-center">Logout</v-list-item-content>
        </v-list-item>
        <v-list-item v-else @click="$modal.show('login-modal')">
          <v-list-item-action></v-list-item-action>
          <v-list-item-content class="text-center">Login</v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app data-html2canvas-ignore="true">
      <span class="hidden-sm-and-up">
        <v-app-bar-nav-icon @click="sidebar = !sidebar">
          <v-icon>menu</v-icon>
        </v-app-bar-nav-icon>
      </span>
      <v-toolbar-title class="ml-3">
        <router-link to="/" tag="span" style="cursor: pointer">
          <v-img :src="getImgUrl('etogether.png')" width="100px" />
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>

      <v-toolbar-items>
        <!-- <div class="menu d-inline" style="height:20px">        -->
        <v-btn
          class="hidden-xs-only justify-content-around"
          text
          width="15%"
          @click="clickproduct"
          style="height:64px"
        >Mart</v-btn>
        <v-btn
          class="hidden-xs-only justify-content-around"
          text
          width="10%"
          @click="clickmap"
          style="height:64px"
        >Map</v-btn>
        <v-btn
          class="hidden-xs-only justify-content-around"
          text
          width="25%"
          v-if="username"
          @click="clickmybudget"
          style="height:64px"
        >My Budget</v-btn>
        <v-btn
          class="hidden-xs-only justify-content-around"
          text
          width="15%"
          @click="clickreview"
          style="height:64px"
        >Review</v-btn>
        <v-btn
          class="hidden-xs-only justify-content-around"
          text
          width="15%"
          v-if="checkAdmin"
          style="height:64px"
          @click="goToAdminPage"
        >Admin</v-btn>
        <v-btn text v-if="username" class="text-center" style="padding-top:20px;">
          <v-menu offset-y open-on-hover>
            <template v-slot:activator="{ on }">
              <span
                class="headerMenu"
                text
                v-on="on"
                style="padding-bottom:17px; font-weight:bold; color:darkblue"
              >
                <i class="material-icons">account_circle</i>
                {{setUserName}}
              </span>
            </template>
            <v-list dense shaped width="150px">
              <v-list-item @click="pwdCheck">
                <v-list-item-action>
                  <v-list-item-content
                    style="font-weight:bold; font-size:15px; color:dimgrey"
                  >My Page</v-list-item-content>
                </v-list-item-action>
              </v-list-item>
              <v-list-item @click="logout">
                <v-list-item-action>
                  <v-list-item-content
                    style="font-weight:bold; font-size:15px; color:dimgrey"
                  >Logout</v-list-item-content>
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-btn>
        <!-- <v-btn v-if="username" text @click="logout">Logout</v-btn> -->
        <v-btn v-else text width="15%" @click="$modal.show('login-modal')">Login</v-btn>
        <!-- <v-btn v-if="!username" text to="/findpwd">비밀번호 찾기</v-btn> -->
      </v-toolbar-items>
    </v-app-bar>
  </div>
</template>

<script>
import LoginModal from "../components/LoginModal";
import http from "../http-common";
export default {
  name: "Header",
  components: {
    LoginModal
  },
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    pwdCheck() {
      // if (this.$session.has("accessToken") && typeof this.$session.get("accessToken") !== undefined) {
      if (sessionStorage.getItem("accessToken") !== undefined) {
        this.$router.push("/pwdcheck");
      } else {
        alert("잘못된 접근입니다.");
      }
    },
    getUserName() {
      this.username = sessionStorage.getItem("user");
    },
    logout() {
      let conf = confirm("로그아웃 하시겠습니까?");
      if (conf == true) {
        http
          .post(
            "/logout",
            {
              email: sessionStorage.getItem("email")
            },
            this.$store.getters.requestHeader
          )
          .then(res => {
            console.log(res);
            if (res.data.state === "succ" && res.data.count == 1) {
              sessionStorage.clear();
              this.getUserName();
              this.getIsAdmin();
              this.$router.push("/");
            }
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    clickproduct() {
      this.$router.push("/product");
    },
    clickmybudget() {
      if (sessionStorage.getItem("accessToken")) {
        this.$router.push("/mybudget");
      } else {
        alert("로그인 후 이용해주세요");
        return;
      }
    },
    clickreview() {
      this.$router.push("/review");
    },
    clickmap() {
      this.$router.push("/map");
    },
    goToAdminPage() {
      console.log("gotoadminpage");
      const authenitcation = sessionStorage.getItem("auth");
      if (authenitcation !== "3") {
        alert("관리자만 접속 가능한 페이지입니다");
        return;
      } else {
        this.$router.push("/admin");
      }
    },
    getIsAdmin() {
      console.log("getIsADMI");
      this.isAdmin = sessionStorage.getItem("auth") === "3" ? true : false;
    }
    // isAdmin(){
    //   this.isAdmin = this.$store.getters.isAdmin
    //   return this.isAdmin
    // }
  },

  data() {
    return {
      appTitle: "Blog",
      sidebar: false,
      menuItems: [
        { title: "Mart", path: "/product" },
        { title: "My Budget", path: "/mybudget" },
        { title: "Review", path: "/review" }
        // { title: "UserInfo", path: "/userinfo" }
        // { title: "비밀번호 찾기", path:"/findpwd"}
      ],
      username: "",
      isAdmin: false
    };
  },

  computed: {
    setUserName() {
      console.log("setUserName");
      console.log(this.username);
      return this.username;
    },
    checkLoggedIn() {
      return this.isLoggedIn;
    },
    checkAdmin() {
      console.log("checkadmin");
      console.log(this.isAdmin);
      return this.isAdmin;
    }
  },
  mounted() {
    this.getUserName();
    this.getIsAdmin();
  }
};
</script>
<style scoped>
.headerMenu > span {
  font-size: 5vw;
}
</style>
