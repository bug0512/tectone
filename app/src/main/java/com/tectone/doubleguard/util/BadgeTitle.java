package com.tectone.doubleguard.util;

/**
 * Created by JamesLee on 2014-11-02.
 */
public class BadgeTitle {

    public String getBadgeTitle(int sencerType, String sencerListName) {
        String rtnString = null;
        if (sencerListName.equals("1,B,1")) {
            if (sencerType == 0) {
                rtnString = "옷장 안에 부착하여 의류 보관에 적합한 환경인지 감지합니다.";
            } else if (sencerType == 2) {
                rtnString = "습도가 몇 %일 때 알림을 받을지 Range를 조정해서 변경할 수 있습니다. 의류 보관 최적환경은 온도 25℃, 습도 40%~60%입니다.";
            } else if (sencerType == 3) {
                rtnString = "어떻게 알림 받을지 세부적인 LED 색을 결정할 수 있습니다.";
            }
        } else if (sencerListName.equals("1,A,1")) {
            if (sencerType == 0) {
                rtnString = "Temperature 센서는 불이 켜진 곳 20 Inch 부근에 놓아두세요. 불을 켜둔 것을 잊어버리고 다른 곳에 있을 때 알려드립니다.";
            } else if (sencerType == 1) {
                rtnString = "주변 온도가 몇℃가 되었을 때 '가스레인지가 켜졌다' 고 간주할지 정할 수 있습니다. Sensor Badge의 위치에 따라 달라지니 Copy 기능을 사용하여 간편하게 설정하세요.";
            } else if (sencerType == 2) {
                rtnString = "어떻게 알림 받을지 오디오 파일을 선택해주세요.";
            }
        } else if (sencerListName.equals("6,N,2")) {
            if (sencerType == 0) {
                rtnString = "Motion 센서는 가스레인지 앞쪽에 놓아두세요. 불을 켜둔 것을 잊어버리고 다른 곳에 있을 때 알려드립니다.";
            } else if (sencerType == 1) {
                rtnString = "얼마나 오래 자리를 비울 때 알림을 받을지 Duration으로 설정할 수 있습니다. 얼마나 멀리 있을 때 근처에 없다고 볼 것인지는 Range에서 설정해주세요.";
            } else if (sencerType == 2) {
                rtnString = "어떻게 알림 받을지 오디오 파일을 선택해주세요.";
            }
        } else if (sencerListName.equals("4,L,3")) {
            if (sencerType == 0) {
                rtnString = "미세한 움직임을 감지하여 침대에 누워 있는지 기상했는지를 파악합니다. 침대 위나 배개 옆면에 부착하면 이제 멀리서도 부모님께 아침 안부 메시지를 드릴 수 있습니다.";
            } else if (sencerType == 1) {
                rtnString = "몇시간 동안 침대에 있어야 '잠들었다' 고 판단할지 Duration을 조정해서 변경할 수 있습니다. 움직임 Range를 낮출수록 민감하게 움직임을 감지합니다.";
            } else if (sencerType == 2) {
                rtnString = "누구에게 메시지를 전송할지 휴대전화 번호를 입력해주세요.";
            }
        } else if (sencerListName.equals("2,C,2")) {
            if (sencerType == 0) {
                rtnString = "화장실 조명이 켜지면 시간이 얼마나 지났는지 카운팅하기 시작합니다.";
            } else if (sencerType == 1) {
                rtnString = "얼마나 밝아야 '화장실 조명이 켜진 것' 으로 볼 것인지 Range로 기준밝기를 정할 수 있습니다. 화장실 조명 세기와 창문이 있는지 여부에 따라 기준밝기가 달라질 수 있습니다.";
            } else if (sencerType == 2) {
                rtnString = "어떤 LED 색으로 시간흐름을 느낄지 정해주세요.";
            }
        } else if (sencerListName.equals("4,L,2")) {
            if (sencerType == 0) {
                rtnString = "현관문 안쪽에 붙여 두세요. 집에 들어오고, 밖으로 나가는 것을 파악하여 비오는 날은 우산 챙겨가시라고 알려드리고, 귀가할 때는 가볍게 맞이할게요.";
            } else if (sencerType == 1) {
                rtnString = "어떤 빠르기로 문이 움직였을 때 '문이 열렸다' 볼 것인지 Range로 조정할 수 있습니다.";
            } else if (sencerType == 2) {
                rtnString = "집에 들어올 때, 밖으로 나갈 때 어떤 이야기를 듣고 싶으세요? 오디오 파일을 선택해주세요.";
            }
        } else if (sencerListName.equals("6,G,2")) {
            if (sencerType == 0) {
                rtnString = "현관문 안쪽에 붙여 두세요. 집에 들어오고, 밖으로 나가는 것을 파악하여 비오는 날은 우산 챙겨가시라고 알려드리고, 귀가할 때는 가볍게 맞이할게요.";
            } else if (sencerType == 1) {
                rtnString = "현관문에 얼마나 가깝게 접근해야 '나간다' 볼 것인지 Range로 조정할 수 있습니다. 현관 주변을 자주 지나가는 분은 Duration을 비교적 길게 잡으시면 좋습니다. ";
            } else if (sencerType == 2) {
                rtnString = "집에 들어올 때, 밖으로 나갈 때 어떤 이야기를 듣고 싶으세요? 오디오 파일을 선택해주세요.";
            }
        } else if (sencerListName.equals("4,E,2")) {
            if (sencerType == 0) {
                rtnString = "꾸준히 사용하고 싶은 물건에 붙여두세요. 얼마나 오래 사용안했는지를 기억했다가 너무 오랫동안 안하면 자극시켜드립니다.";
            } else if (sencerType == 1) {
                rtnString = "얼마나 오래 안하면 알림을 받을지 Duration으로 조정할 수 있습니다. 사용하지 않은지 몇 시간이 지나면 알림을 받을지 결정하세요.";
            } else if (sencerType == 2) {
                rtnString = "어떤 알림을 받을지 골라주세요. 중요한 것이면 자극적인 멘트를 골라보는 것도 재밌을거에요.";
            }
        } else if (sencerListName.equals("5,F,5")) {
            if (sencerType == 0) {
                rtnString = "현관문 바깥쪽에 붙여두세요. 집 앞에서 서성이는 외부인을 감지하여 알려드립니다.";
            } else if (sencerType == 1) {
                rtnString = "현관 앞에 얼마나 오래 머물러야 알림을 보내고, TV를 켤 지 Duration으로 조정할 수 있습니다.";
            } else if (sencerType == 2) {
                rtnString = "사람이 있는 척 TV를 켜둡니다. 몇 분 후에 TV를 다시 OFF 할 지 정할 수 있습니다.";
            }
        } else if (sencerListName.equals("4,L,1")) {
            if (sencerType == 0) {
                rtnString = "연인이 생각날 때 Sensor Badge를 흔드면 그 사람의 Sensor Badge 색이 변할거에요. 만약 텔레파시가 통해서 동시에 흔들었다면 어떻게 될까요?";
            } else if (sencerType == 1) {
                rtnString = "어떤 빠르기로 흔들었을 때 신호를 보낼지 Range로 조절할 수 있습니다. 얼마나 오래 흔들어야 될지는 Duration에서 결정해주세요.";
            } else if (sencerType == 2) {
                rtnString = "전달되는 LED 색을 결정할 수 있습니다.";
            }
        } else if (sencerListName.equals("4,E,1")) {
            if (sencerType == 0) {
                rtnString = "연인이 생각날 때 Sensor Badge를 흔드면 그 사람의 Sensor Badge 색이 변할거에요. 만약 텔레파시가 통해서 동시에 흔들었다면 어떻게 될까요?";
            } else if (sencerType == 1) {
                rtnString = "어떤 빠르기로 흔들었을 때 신호를 보낼지 Range로 조절할 수 있습니다. 얼마나 오래 흔들어야 될지는 Duration에서 결정해주세요.";
            } else if (sencerType == 2) {
                rtnString = "전달되는 LED 색을 결정할 수 있습니다.";
            }
        } else if (sencerListName.equals("4,L,2")) {
            if (sencerType == 0) {
                rtnString = "지금 비가 오는지 확인하려고 창문을 기웃거리지 말고 가볍게 톡톡 두드려 보세요. 오늘의 날씨를 재밌게 알려드립니다.";
            } else if (sencerType == 1) {
                rtnString = "얼마나 세게 Sensor Badge를 건들였을 때 알림을 받을지 Range에서 조절할 수 있습니다.";
            } else if (sencerType == 2) {
                rtnString = "어떤 컨셉으로 날씨 정보를 받을지 선택할 수 있습니다.";
            }
            //2014.11.19 추가
        } else if (sencerListName.equals("1,I,4")) {
            if (sencerType == 0) {
                rtnString = "아기가 최적의 습도에서 생활할 수 있도록 습도를 파악하고 일정 범위 이상 시, 제습기를 가동합니다.";
            } else if (sencerType == 1) {
                rtnString = "습도가 몇 %일 때 알림을 받을지 범위를 조정해서 변경할 수 있습니다. 아기의 최적환경은 온도 25℃, 습도 40%~60%입니다.";
            } else if (sencerType == 2) {
                rtnString = "측정된 센서값에 대한 다양한 제품들의 특정 기능을 실행할 수 있습니다.";
            }
        } else if (sencerListName.equals("5,F,5")) {
            if (sencerType == 0) {
                rtnString = "아기가 TV 시청 시, 눈이 나빠지지 않도록 TV와의 겨리 정보를 제공합니다.";
            } else if (sencerType == 1) {
                rtnString = "아기가 눈이 나빠지지 않는 TV 시청거리를 변경할 수 있으며. 20인치 이내로 접근하면 TV가 꺼집니다.";
            } else if (sencerType == 2) {
                rtnString = "측정된 센서값에 대한 TV의 특정 기능을 실행할 수 있습니다.";
            }
        } else if (sencerListName.equals("5,F,2")) {
            if (sencerType == 0) {
                rtnString = "아기가 위험한 곳이나 가지말아야할 장소와의 거리 정보를 제공합니다.";
            } else if (sencerType == 1) {
                rtnString = "아기가 가까이하지 말아야할 장소의 거리 조절이 가능합니다. 10인치 이내로 접근하면 경고음이 발생합니다.";
            } else if (sencerType == 2) {
                rtnString = "아기가 가까이 접근할 수 없도록 다양한 경고음을 선택할 수 있습니다.";
            }
        } else if (sencerListName.equals("3,K,4")) {
            if (sencerType == 0) {
                rtnString = "아기의 울음소리를 감지할 수 있습니다.";
            } else if (sencerType == 1) {
                rtnString = "아기의 울음소리를 감지할 수 있도록 마이크 감도 조절할 수 있습니다. 일반적으로 10 레벨이 적당합니다.";
            } else if (sencerType == 2) {
                rtnString = "측정된 센서값을 다른 배지로 전달 가능 합니다.";
            }
        } else if (sencerListName.equals("6,G,1")) {
            if (sencerType == 0) {
                rtnString = "사용자가 위치한 장소로 알림 메시지를 전달합니다.";
            } else if (sencerType == 1) {
                rtnString = "부모가 있는 공간으로 LED 알림 전달합니다.";
            } else if (sencerType == 2) {
                rtnString = "조명 컬러 변경 가능합니다.";
            }
        } else {
            rtnString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer blandit lacus augue, fermentum cursus mauris facilisis id. Aenean id accumsan leo.";
        }

        return rtnString;
    }
}
