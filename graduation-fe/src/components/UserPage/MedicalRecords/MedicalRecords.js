import TabDetailedExaminationRecords from "./DetailedExaminationRecords/TabDetailedExaminationRecords";
import ImgHeaderMedicalRecords from "./ImgHeaderMedicalRecords";
import ListOfMedicalRecords from "./ListOfMedicalRecords/ListOfMedicalRecords";
import { useState } from "react";

export default function MedicalRecords() {


   
 
    let navActive="";
    let show="";
    
    if(sessionStorage.getItem("bookingId")!==null){
        navActive="";
        show="";
    }else{
        navActive="active";
        show="show active"
    }
//CHUYỂN TAB
  
    const[updateStatus,setUpdateStatus]=useState(false);

    
    return (
        <div>
            <ImgHeaderMedicalRecords />
            <nav style={{ backgroundColor: 'rgb(246, 249, 249)', marginTop: '10px' }}>
                <div className="nav nav-tabs mx-5" id="nav-tab" role="tablist">
                    <button className={`nav-link `+navActive} id="nav-danhsachhoso-tab"
                        data-bs-toggle="tab" data-bs-target="#nav-danhsachhoso" type="button"
                        role="tab" aria-controls="nav-danhsachhoso" aria-selected="true">
                        Danh sách hồ sơ khám
                    </button>
                    {
                        
                        sessionStorage.getItem("bookingId")!==null ? (
                            <button className={navActive==="active"?`nav-link`:`nav-link active`} id="nav-chitiethoso-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-chitiethoso" type="button" role="tab"
                                aria-controls="nav-chitiethoso" aria-selected="false">
                                Chi tiết hồ sơ khám
                            </button>
                        ) : ""
                    }

                </div>
            </nav>

            {/* CONTENT */}
            <div className="tab-content" id="nav-tabContent">
                <div className={`tab-pane fade `+show} id="nav-danhsachhoso"
                    role="tabpanel" aria-labelledby="nav-danhsachhoso-tab">
                    <ListOfMedicalRecords  updateStatus={updateStatus}
                    // setShow1={setShow1} setShow2={setShow2} 
                    />
                </div>
                {
                    sessionStorage.getItem("bookingId")!==null ?
                        (
                            <div className={show==="show active"?`tab-pane fade`:`tab-pane fade show active`} id="nav-chitiethoso"
                                role="tabpanel" aria-labelledby="nav-chitiethoso-tab">
                                <TabDetailedExaminationRecords 
                                    setUpdateStatus={setUpdateStatus}/>
                            </div>
                        ) : ""
                }
            </div>
            {/* END CONTENT */}
        </div>
    )
}