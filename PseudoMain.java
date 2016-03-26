public class PseudoMain {

	static PDFtoDB_Insert begin = new PDFtoDB_Insert("se2012.pdf");
	static Display_OnScreen disp = new Display_OnScreen();
	static DB_Queries q = new DB_Queries();
	//static DB_Init init = new DB_Init();

	public void beginImport(){
		try {
			//begin.start_insert(pdf_location);
			// Multiple filters
		//	init.filtered_collection = q.filter_by_name("ak",init.filtered_collection).getCollection();
			//disp.display(q.sort_by_subject("SIGNALS & SYSTEMS (PP)", 'S', init.filtered_collection));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
