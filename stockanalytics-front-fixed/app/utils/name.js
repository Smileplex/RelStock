const antNames = [
  '톱니침개미',
  '스미스침개미',
  '왕침개미',
  '일본침개미',
  '털보장님침개미',
  '장님침개미',
  '황침개미',
  '나도황개미',
  '사우터침개미',
  '침개미',
  '거치른침개미',
  '굽은침개미',
  '와타세침개미',
  '시베리아개미',
  '흰발마디개미',
  '민마디개미',
  '루톤민마디개미',
  '루톤마디개미',
  '루톤소공마디개미',
  '흰발납작자루개미',
  '검정꼬리치레개미',
  '짱구개미',
  '극동흑개미',
  '애집개미',
  '고마개미',
  '한국홍가슴개미',
  '검정네눈개미',
  '한라왕개미',
  '제주왕개미',
  '사할린왕개미',
  '북방왕개미',
  '홍개미',
  '불개미',
  '사무라이개미',
];

export default () =>
  antNames[Math.floor(Math.random() * (antNames.length - 0) + 0)] +
  Math.floor(Math.random() * 100);
