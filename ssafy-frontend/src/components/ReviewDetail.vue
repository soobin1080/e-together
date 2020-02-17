<template>
  <div class="main">
    <!-- <div class="container" style="height:100%; width:50%;"> -->
    <div class="card mb-3" style="height:100%;">
      <div class="row no-gutters">
        <div class="col-md-4">
          <img src class="card-img" alt />{{review.img}}
        </div>
        <div class="col-md-8">
          <div class="card-body mt-8">
            <h4
              class="card-title text-center"
            >{{budgetInfo.budget_title}}</h4>
            <div class="text-center">
              <p class="card-text">{{review.review_content}}</p>
              <p class="card-text">
                <small class="text-muted">{{dateParsing(review.review_date)}}</small>
              </p>
              <i class="fas fa-heart text-danger mr-1 mb-3"></i>
              {{like_users.length}}
              <span
                id="likeBtn"
                class="badge badge-pill badge-primary"
                style="cursor:pointer"
                @click="like(review)"
              >좋아요</span>
              <br />
              <!-- <span v-else 
                class="badge badge-pill badge-primary" 
                style="cursor:pointer"
              @click="like(review)">좋아요</span>-->
              <p
                v-if="budgetInfo.suitability === 1"
                class="text-center d-inline-block bg-primary rounded"
                style="color:white"
              > 사용자가 적합하다고 판단한 예산입니다. </p>
              <p
                v-else-if="budgetInfo.suitability == 2"
                class="text-center bg-warning rounded"
                style="color:white"
              > 사용자가 부적합하다고 판단한 예산입니다. </p>
              <p
                v-else
                class="text-center bg-secondary rounded"
                style="color:white"
              > 사용자가 아직 적합도를 정하지 않았습니다. </p>
            </div>
            <p
              class="text-center font-weight-bolder"
            >인원 : {{budgetInfo.personnel}} / 예산 : {{budgetInfo.budget}}</p>
            <a
              href="javascript:;"
              @click="kakaotalklink"
              id="kakao-link-btn"
              style="width:30px; height:auto"
            >
              <!-- 버튼이 생기는 부분, id는 맘대로 쓰시되 아래 js 코드도 동일하게 적용해주셔야 합니다. -->
              <img
                src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
                style="width:30px; height:auto"
              />
              <!-- 톡 이미지 부분이고, 전 kakaolink_btn_small.png로 불러왔습니다.   -->
            </a>

            <!-- <p class="text-center" 
              data-toggle="modal" 
              data-target="#exampleModalLong" 
              style="cursor:pointer">
              예산 전체보기
            </p>-->
            <!-- <div v-for="budget in this.budgetList">
              {{ budget.pro_name }} * {{budget.quantity}} = {{budget.price}}
            </div>-->
          </div>
          <!-- 
          
  

          <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLongTitle">장바구니 전체</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                  <ul class="list-group list-group-flush">
                    <li v-for="product in budgetList" class="list-group-item">
                      {{product.pro_name}} * {{product.quantity}}
                    </li>
                  </ul>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
          </div>-->
        </div>

        <div class="col-md-12">
          <table style="width:100%; margin:auto; text-align:center">
            <col width="55%" />
            <col width="15%" />
            <col width="30%" />
            <thead>
              <tr>
                <th style="background-color: #fffeaa; color:#3d2300">항목</th>
                <th style="background-color: #fffeaa; color:#3d2300">수량</th>
                <th style="background-color: #fffeaa; color:#3d2300">가격</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="product in budgetList" :key="product">
                <td v-html="product.pro_name"></td>

                <td v-html="product.quantity"></td>

                <td class="pro_price">{{product.price}} 원</td>
              </tr>

              <tr>
                <th colspan="2">합계</th>
                <td>{{this.total}} 원</td>
              </tr>
              <tr>
                <th colspan="2">잔액</th>
                <td>{{budgetInfo.budget-this.total}} 원</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!-- </div> -->

    <div style="text-align:right">
      <v-btn outlined color="success"  class="mr-4" @click="gotoList">목록</v-btn>

      <!-- 작성자랑 지금 로그인한 사용자가 같아야만 이 버튼이 보임 -->
      <span v-if="checkUser()==true">
      <v-btn outlined color="error"  class="mr-4" @click="deleteReview(review.review_num)">삭제</v-btn>
      </span>
    </div>
  </div>
</template>

<script>
import EventBus from "../event-bus";
import http from "../http-common";
import Kakao from "../services/KakaotalkService";
export default {
  name: "ReviewDetail",

  data() {
    return {
      review: Object,
      budgetInfo: Object,
      budgetList: Array,
      like_users: Array,
      reviewNum: Number,
      likeStatus: false,
      dialogm1: "",
      dialog: false,
      total: 0,
      img: ""
    };
  },

  methods: {
    checkUser(){
      if(this.budgetInfo.user_email==sessionStorage.getItem("email")){
        return true;
      }else{
        return false;
      }
     
    },
    gotoList(){
      this.$router.push("/review");
    },
    deleteReview(num){
      console.log(num)
       let conf = confirm('리뷰를 삭제하시겠습니까?')
       if(conf){
       http
        .delete("/review/"+num)
        .then(response => {
          console.log(response) 
            if(response.status==200){
              alert("삭제 처리 하였습니다!");
              this.$router.push("/review");
            } else{
              alert("삭제 처리하지 못하였습니다!");
            }
        })
        .catch((err) => {
          console.log(err)
          this.errored = true;
        })
        .finally(() => (this.loading = false));
       }else{
         return;
       }
    },
    kakaotalklink() {
      //<![CDATA[
      // // 사용할 앱의 JavaScript 키를 설정해 주세요.
      Kakao.init("918cb2cf36b91b4f11f0a63002dc2154");
      // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
      Kakao.Link.createDefaultButton({
        container: "#kakao-link-btn", // 컨테이너는 아까 위에 버튼이 쓰여진 부분 id
        objectType: "feed",
        content: {
          // 여기부터 실제 내용이 들어갑니다.
          title: this.budgetInfo.budget_title, // 본문 제목
          description: this.review.review_content, // 본문 바로 아래 들어가는 영역?
          imageUrl: "../etogether.png", // 이미지
          link: {
            mobileWebUrl: "https://developers.kakao.com",
            webUrl: "https://developers.kakao.com"
          }
        },
        social: {
          /* 공유하면 소셜 정보도 같이 줄 수 있는데, 이 부분은 기반 서비스마다 적용이 쉬울수도 어려울 수도 있을듯 합니다. 전 연구해보고 안되면 제거할 예정 (망할 google  blogger...) */
          likeCount: this.like_users.length
          // commentCount: 45,
          // sharedCount: 845
        },
        buttons: [
          {
            title: "e투계더로 이동",
            link: {
              mobileWebUrl: "https://developers.kakao.com",
              webUrl: "https://developers.kakao.com"
            }
          }
        ]
      });
    },
    total_sum() {
      console.log(this.budgetList);
      // console.log(this.budgetList)
    },
    dateParsing(beforeParsing) {
      const t = beforeParsing.indexOf("T");
      const afterParsing = beforeParsing.substring(0, t);
      // console.log(afterParsing)
      const realdate =
        afterParsing.substring(0, 4) +
        "년 " +
        afterParsing.substring(5, 7) +
        "월 " +
        (Number(afterParsing.substring(8, 11)) + 1) +
        "일";
      console.log("realdate: " + realdate);
      return realdate;
    },
    getReviewDetailByArg(review_num) {
      let loginUser = sessionStorage.getItem("email");
      http
        .get(
          `/review/${this.reviewNum}`,
          { review_num: this.reviewNum },
          this.$store.getters.requestHeader
        )
        .then(res => {
          console.log(res);
          this.review = res.data.review;
          this.budgetInfo = res.data.budgetinfo;
          this.budgetList = res.data.budgetlist;
          this.like_users = res.data.like_user;
          let loginUser = sessionStorage.getItem("email");
          var likeBtn = document.getElementById("likeBtn");
          console.log(likeBtn);
          if (this.like_users.includes(loginUser)) {
            likeBtn.innerHTML = "좋아요 취소";
            likeBtn.className = "badge badge-pill badge-danger";
          } else {
            likeBtn.innerHTML = "좋아요";
            likeBtn.className = "badge badge-pill badge-primary";
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getReviewDetail() {
      this.reviewNum = this.$route.params.reviewNum;
      console.log("getReviewDetail");
      http
        .get(
          `/review/${this.reviewNum}`,
          { review_num: this.reviewNum },
          this.$store.getters.requestHeader
        )
        .then(res => {
          console.log(res);
          this.review = res.data.review;
          this.budgetInfo = res.data.budgetinfo;
          this.budgetList = res.data.budgetlist;
          console.log(this.budgetList);
          this.total = this.budgetList.reduce(
            (total, budget) => (total += budget.price * budget.quantity),
            0
          );
          this.like_users = res.data.like_user;
          let loginUser = sessionStorage.getItem("email");

          if (this.like_users.includes(loginUser)) {
            document.getElementById("likeBtn").innerHTML = "좋아요 취소";
            document.getElementById("likeBtn").className =
              "badge badge-pill badge-danger";
          } else {
            document.getElementById("likeBtn").innerHTML = "좋아요";
            document.getElementById("likeBtn").className =
              "badge badge-pill badge-primary";
          }
        })
        .catch(err => {
          console.log(err);
        });
    },

    like(review) {
      let loginUser = sessionStorage.getItem("email");
      if (loginUser === null || typeof loginUser === undefined) {
        alert("로그인이 필요합니다.");
        return;
      }
      // console.log(review)
      if (this.like_users.includes(loginUser)) {
        console.log("delete");
        console.log(review.review_num);
        console.log(loginUser);
        http
          .delete(
            `/review/like_count/${this.review.review_num}/${loginUser}`,
            this.$store.getters.requestHeader
          )
          .then(res => {
            this.getReviewDetail(loginUser);
            console.log(res);
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        console.log("like");
        http
          .post(
            `/review/like_count`,
            {
              review_num: review.review_num,
              user_email: loginUser
            },
            this.$store.getters.requestHeader
          )
          .then(res => {
            console.log(res);
            this.getReviewDetailByArg(loginUser);
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    likeStatus() {
      // $(document).ready(function() {
      console.log("likeStatus");
      var likeBtn = document.getElementById("likeBtn");
      console.log(likeBtn);
      // });
    }
  },
  mounted() {
    this.getReviewDetail();
    // this.likeStatus();
    this.total_sum();
  },

  computed: {
    convertDate() {
      console.log("convertDate");
      let convertDate = new Date(this.date);
      console.log(convertDate);
      return this.date;
    },

    computedReviewNum() {
      return this.$route.params.reviewNum;
    }
  }
};
</script>

<style scoped>
.main {
  padding-top: 80px;
  padding-bottom: 80px;
  margin: auto;
  width: 80%;
}
table {
  width: 100%;
  border-top: 1px solid dimgray;
  border-collapse: collapse;
}
th,
td {
  border-bottom: 1px solid dimgray;
  padding: 5px;
}
th {
  color: dimgray;
  background-color: lightgray;
}
img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}
</style>