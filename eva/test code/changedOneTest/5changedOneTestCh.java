List<String> l = new ArrayList<String>();
		MailReader mr = new MailReader("5.eml");
		MailParser mp = new MailParser(mr);
		mp.splitMail(mr);
		mp.setHeader();
		mp.setBody();
		l = mp.getBody().getBodyList();
		
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(0);	
		
		List<String> l3 = new ArrayList<String>();
		MailReader mr2 = new MailReader("5changedOne.eml");
		MailParser mp2 = new MailParser(mr);
		mp2.splitMail(mr2);
		mp2.setHeader();
		mp2.setBody();
		l3 = mp2.getBody().getBodyList();
		
		Chameleon ch = new Chameleon();
		ChameleonSigner cs = new ChameleonSigner(l2, l, ch);
		cs.setListWithChamHash();
		ChameleonEditor ce = new ChameleonEditor(l2, l3, ch, cs.getId());
		ce.setItemList(l);
		ce.calcHashWithInfoFromSigner(cs);
		String combine = cs.combineInfos();
		String signature = cs.sign(combine);
		ChameleonVerifier cv = new ChameleonVerifier(ce.getItemList2(), ch);
		cv.calcHashWithInfoFromEditor(ce);
		String combine2 = cv.combineInfos();
		boolean b = cs.getRsaSig().verify(combine2, signature, cs.getPublicKey());
		print(b);