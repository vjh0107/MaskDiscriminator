# MaskDiscriminator with Deep Learning & Raspberry Pi / 라즈베리 파이에서 딥러닝을 이용한 마스크 판독기

![KakaoTalk_20201105_161538654](https://user-images.githubusercontent.com/66157320/98209661-a8045d80-1f82-11eb-9b71-858356d9527b.jpg)


One Paragraph of project description goes here / 프로젝트의 전반적인 내용에 대한 요약을 여기에 적습니다

## Getting Started / 어떻게 시작하나요?

### Prerequisites / 선행 조건

아래 사항들은 제가 개발한 환경입니다.

소프트웨어:
  - MacOS (저는 macos에서 시작하나 윈도우에서도 충분히 가능합니다.)
  - Pycharm Ultimate (무료버전을 사용할 경우 interpreter 사용이 불가능합니다.)
  - 라즈베리파이 os (https://www.raspberrypi.org/downloads/)
  
하드웨어:
  - MacOS Catalina 를 지원하는 기기
  - 라즈베리파이 B+ (라즈베리파이 os가 설치되어있고 8GB 이상의 저장소를 가지고 있는 기기)
  - pi camera 5MP
  - intel Movidius Neural Compute Stick 2
  
```
11
```

### Installing / 설치

아래 사항들로 현 프로젝트에 관한 모듈들을 설치할 수 있습니다
<div>
<img width="400" alt="Screen Shot 2020-11-05 at 5 26 21 PM" src="https://user-images.githubusercontent.com/66157320/98217500-ebb09480-1f8d-11eb-81f0-cafded6f3d01.png">
<img width="400" alt="Screen Shot 2020-11-05 at 5 42 20 PM" src="https://user-images.githubusercontent.com/66157320/98217809-55c93980-1f8e-11eb-81fb-3bf5ea6f05f4.png">
</div>

project는 기본 pure python으로 생성해주세요


```
+ New Project -> 프로젝트 설정 후 -> CREATE
```

<img width="1792" alt="Screen Shot 2020-11-05 at 6 17 51 PM" src="https://user-images.githubusercontent.com/66157320/98221691-4ac4d800-1f93-11eb-81f8-0f5e56d1dde2.png">

프로젝트 생성이 끝났다면 이제 라즈베리파이와 remote host를 연결해줄 설정창을 엽니다

```
Tools -> Deployment -> Configuration…
```

<div>
<img width="300" alt="Screen Shot 2020-11-05 at 5 56 31 PM" src="https://user-images.githubusercontent.com/66157320/98221537-18b37600-1f93-11eb-9c4f-29ebd251c2ca.png">
<img width="135" alt="Screen Shot 2020-11-05 at 6 25 00 PM" src="https://user-images.githubusercontent.com/66157320/98222506-4351fe80-1f94-11eb-97e6-38947ec7e0ea.png">
<img width="330" alt="Screen Shot 2020-11-05 at 5 57 18 PM" src="https://user-images.githubusercontent.com/66157320/98222126-c888e380-1f93-11eb-9704-f361b28be697.png">
</div>

SFTP 통신을 할 서버를 추가합니다

```
좌측 상단 + -> SFTP -> 서버이름 정한 뒤 OK
```
![Cap 2017-07-03 15-46-02-275](https://user-images.githubusercontent.com/66157320/98226515-7b0f7500-1f99-11eb-96ae-5e75026cd2b5.png)

라즈베리파이의 설정에서 SSH 통신과 카메라 사용여부를 Enable 해주세요

```
Preferences -> Raspberry Pi Configuration -> Interfaces -> SSH Enable, Camera Enable
```

<div>
<img width="250" alt="Screen Shot 2020-11-05 at 6 01 02 PM" src="https://user-images.githubusercontent.com/66157320/98223048-ec98f480-1f94-11eb-811e-ef2a10e938f1.png">
<img width="250" alt="Screen Shot 2020-11-05 at 6 01 28 PM" src="https://user-images.githubusercontent.com/66157320/98223063-f4589900-1f94-11eb-8d24-67b7e2cbe8f4.png">
<img width="400" alt="Screen Shot 2020-11-05 at 6 02 01 PM" src="https://user-images.githubusercontent.com/66157320/98223083-f9b5e380-1f94-11eb-8980-c4a061c51ade.png">
</div>

SFTP 통신을 위해 먼저 SSH 호스트를 등록시켜줍니다

Host에는 아이피 주소를 넣습니다. Username의 디폴트는 pi 이며, password의 default는 raspberry입니다

TEST CONNECTION 에서 SUCCESS가 뜨시는지 확인해주세요

```
SSH configuration 우측 ... -> 좌측 상단 + -> Host -> OK
```

 <img width="912" alt="Screen Shot 2020-11-05 at 7 10 30 PM" src="https://user-images.githubusercontent.com/66157320/98227380-99c23b80-1f9a-11eb-8c9e-d2b31ac82c1c.png">
 
등록했다면 OK 해준다.

<img width="1904" alt="Screen Shot 2020-11-05 at 7 27 43 PM" src="https://user-images.githubusercontent.com/66157320/98229236-fe7e9580-1f9c-11eb-971f-101610347e38.png">

이제 ide에서 terminal이나 라즈베리파이의 저장소에 접근할 수 있게 되어 작업 후 라즈베리파이에서 실행시킬 수 있게된다.



## Running the tests / 테스트의 실행

어떻게 테스트가 이 시스템에서 돌아가는지에 대한 설명을 합니다

### 테스트는 이런 식으로 동작합니다

왜 이렇게 동작하는지, 설명합니다

```
예시
```

### 테스트는 이런 식으로 작성하시면 됩니다

```
예시
```

## Deployment / 배포

오픈소스로 배포되었습니다

## Built With Who? / 누가 만들었나요?

* 박준형(vjh0107@naver.com)

## Contributor / 기여자

* 금정고등학교 황인환 선생님(wooryi@naver.com)

## License / 라이센스

This project is licensed under the MIT License - see the [LICENSE.md](https://https://github.com/vjh0107/MaskDiscriminator/blob/main/LICENSE) file for details / 이 프로젝트는 MIT 라이센스로 라이센스가 부여되어 있습니다. 자세한 내용은 LICENSE.md 파일을 참고하세요.

## Acknowledgments / 감사의 말

원하는 작품을 만들게 지원해주신 제 모교와 소프트웨어 동아리 황인환 선생님께 감사의 말씀을 드립니다.

* Hat tip to anyone whose code was used / 코드를 사용한 모든 사용자들에게 팁
* Inspiration / 영감
* etc / 기타
