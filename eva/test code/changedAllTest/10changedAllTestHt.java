List<String> l = new ArrayList<String>();
		MailReader mr = new MailReader("10.eml");
		MailParser mp = new MailParser(mr);
		mp.splitMail(mr);
		mp.setHeader();
		mp.setBody();
		l = mp.getBody().getBodyList();
		
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(0);
		l2.add(1);
		l2.add(2);
		l2.add(3);
		l2.add(4);
		l2.add(5);
		l2.add(6);
		l2.add(7);
		l2.add(8);
		l2.add(9);
		l2.add(10);
		
		List<String> l3 = new ArrayList<String>();
		MailReader mr2 = new MailReader("10changedAll.eml");
		MailParser mp2 = new MailParser(mr);
		mp2.splitMail(mr2);
		mp2.setHeader();
		mp2.setBody();
		l3 = mp2.getBody().getBodyList();
		
		HtSigner ts = new HtSigner(l, l2);
		HtEditor te = new HtEditor(l, l3, l2, ts.getSetList());
		HtVerifier tv = new HtVerifier(te.sendMsg(), te.getSetList(), te.getReqNodesList());
		tv.setChangeableIndex(te.getChangeableIndex());
		String combine = ts.combineInfos();
		String signature = ts.sign(combine);
		String combine2 = tv.combineInfos();
		boolean b = ts.getRsaSig().verify(combine2, signature, ts.getPublicKey());
		print(b);