import TabListLichLamViec from "./DanhSachLichLamViec/TabListLichLamViec";
import TabChiTietLichlamViec from "./ChiTietLichLamViec/TabChiTietLichLamViec";
export default function LichLamViec() {
    return (
        <div>
            <nav style={{backgroundColor:'rgb(246, 249, 249)'}} className="">
                <div className="nav nav-tabs mx-5" id="nav-tab" role="tablist">
                    <button className="nav-link active" id="nav-danhsachhoso-tab" 
                    data-bs-toggle="tab" data-bs-target="#nav-danhsachhoso" type="button" 
                    role="tab" aria-controls="nav-danhsachhoso" aria-selected="true">
                        Danh sách đơn đặt lịch
                    </button>
                    <button className="nav-link" id="nav-chitiethoso-tab" data-bs-toggle="tab"
                     data-bs-target="#nav-chitiethoso" type="button" role="tab" 
                     aria-controls="nav-chitiethoso" aria-selected="false">
                         Chi tiết đơn đặt lịch
                    </button>
                    
                </div>
            </nav>

            {/* CONTENT */}
            <div className="tab-content" id="nav-tabContent">
                <div className="tab-pane fade show active" id="nav-danhsachhoso" 
                role="tabpanel" aria-labelledby="nav-danhsachhoso-tab">
                   <TabListLichLamViec/>
                </div>
                <div className="tab-pane fade" id="nav-chitiethoso" 
                role="tabpanel" aria-labelledby="nav-chitiethoso-tab">
                  <TabChiTietLichlamViec/>
                </div>
            </div>
            {/* END CONTENT */}
        </div>
    )
}