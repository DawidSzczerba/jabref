package org.jabref.gui.importer.fetcher;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jabref.Globals;
import org.jabref.logic.importer.WebFetchers;
import org.jabref.logic.journals.JournalAbbreviationLoader;

public class EntryFetchers {

    private final List<EntryFetcher> entryFetchers = new LinkedList<>();

    public EntryFetchers(JournalAbbreviationLoader abbreviationLoader) {
        entryFetchers.add(new CiteSeerXFetcher());
        entryFetchers.add(new IEEEXploreFetcher(abbreviationLoader));
        // entryFetchers.add(new OAI2Fetcher()); - new arXiv fetcher in place, see below

        WebFetchers.getSearchBasedFetchers(Globals.prefs.getImportFormatPreferences()).stream()
                .map(SearchBasedEntryFetcher::new)
                .forEach(entryFetchers::add);
    }

    public List<EntryFetcher> getEntryFetchers() {
        return Collections.unmodifiableList(this.entryFetchers);
    }
}
