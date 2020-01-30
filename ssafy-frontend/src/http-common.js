import axios from "axios";

export default axios.create({
 baseURL: "http://192.168.31.106:8197/ssafyvue/api"
//  headers: {
//   "Content-type": "application/xml",
// }
});