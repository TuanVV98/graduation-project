import Book from "components/UserPage/Book/Book";
import HomePage from "components/UserPage/HomePage/HomePage";
import ShowDentist from "components/UserPage/HomePage/ShowDentist";
import MedicalRecords from "components/UserPage/MedicalRecords/MedicalRecords";
import DetailedNews from "components/UserPage/News/DetailedNews";
import Register from "components/UserPage/Register/Register";
import UserInformation from "components/UserPage/UserInformation/UserInformation";

let routerUser = [
  {
    path: "/home-page",
    name: "Trang chủ",
    component: HomePage,
    layout: "/user",
  },
  {
    path: "/booking",
    name: "Đặt lịch",
    component: Book,
    layout: "/user",
  },
  {
    path: "/dentist",
    name: "Nha sĩ",
    component: ShowDentist,
    layout: "/user",
  },
  {
    path: "/news",
    name: "Tin tức",
    component: DetailedNews,
    layout: "/user",
  },
  {
    path: "/medicalRecords",
    name: "Hồ sơ khám",
    component: MedicalRecords,
    layout: "/user",
  },
  {
    path: "/acc/register",
    name: "Đăng ký",
    component: Register,
    layout: "/user",
  },
  {
    path: "/userInformation",
    name: "Thông tin người dùng",
    component: UserInformation,
    layout: "/user",
  }
];

export default routerUser;
